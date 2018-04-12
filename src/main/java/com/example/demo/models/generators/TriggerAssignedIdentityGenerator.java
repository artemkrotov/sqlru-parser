package com.example.demo.models.generators;

import com.example.demo.models.Identifiable;
import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.AbstractPostInsertGenerator;
import org.hibernate.id.IdentifierGeneratorHelper;
import org.hibernate.id.PostInsertIdentityPersister;
import org.hibernate.id.SequenceIdentityGenerator;
import org.hibernate.id.insert.AbstractReturningDelegate;
import org.hibernate.id.insert.IdentifierGeneratingInsert;
import org.hibernate.id.insert.InsertGeneratedIdentifierDelegate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TriggerAssignedIdentityGenerator extends AbstractPostInsertGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        if (obj instanceof Identifiable) {
            Long id = ((Identifiable) obj).getId();
            if (id != null && id > 0) {
                return super.generate(s, obj);
            }
        }
        return super.generate(s, obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InsertGeneratedIdentifierDelegate getInsertGeneratedIdentifierDelegate(
            PostInsertIdentityPersister persister, Dialect dialect, boolean isGetGeneratedKeysEnabled) {
        return new Delegate(persister, dialect);
    }

    private static class Delegate extends AbstractReturningDelegate {

        private final Dialect dialect;
        private String[] keyColumns;

        Delegate(PostInsertIdentityPersister persister, Dialect dialect) {
            super(persister);
            this.dialect = dialect;

            this.keyColumns = getPersister().getRootTableKeyColumnNames();
            if (keyColumns.length > 1) {
                throw new HibernateException(
                        "trigger assigned identity generator cannot be used with multi-column keys");
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public IdentifierGeneratingInsert prepareIdentifierGeneratingInsert() {
            return new SequenceIdentityGenerator.NoCommentsInsert(dialect);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected PreparedStatement prepare(String insertSQL, SharedSessionContractImplementor session) throws SQLException {
            return session.connection().prepareStatement(insertSQL, keyColumns);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Serializable executeAndExtract(PreparedStatement insert, SharedSessionContractImplementor session)
                throws SQLException {
            insert.executeUpdate();
            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                return IdentifierGeneratorHelper.getGeneratedIdentity(generatedKeys, keyColumns[0], getPersister()
                        .getIdentifierType(), null);
            }
        }
    }
}