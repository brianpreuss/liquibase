package liquibase.database.statement.generator;

import liquibase.database.statement.AddDefaultValueStatement;
import liquibase.database.statement.syntax.Sql;
import liquibase.database.statement.syntax.UnparsedSql;
import liquibase.database.Database;
import liquibase.database.SybaseDatabase;
import liquibase.database.OracleDatabase;
import liquibase.exception.LiquibaseException;
import liquibase.exception.JDBCException;

public class AddDefaultValueGeneratorOracle extends AddDefaultValueGenerator {
    public int getSpecializationLevel() {
        return SPECIALIZATION_LEVEL_DATABASE_SPECIFIC;
    }

    public boolean isValidGenerator(AddDefaultValueStatement statement, Database database) {
        return database instanceof OracleDatabase;
    }

    public Sql[] generateSql(AddDefaultValueStatement statement, Database database) throws JDBCException {
        return new Sql[] {
                new UnparsedSql("ALTER TABLE " + database.escapeTableName(statement.getSchemaName(), statement.getTableName()) + " MODIFY " + database.escapeColumnName(statement.getSchemaName(), statement.getTableName(), statement.getColumnName()) + " DEFAULT " + database.convertJavaObjectToString(statement.getDefaultValue()))
        };
    }
}