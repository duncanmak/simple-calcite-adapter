import org.apache.calcite.DataContext;
import org.apache.calcite.config.CalciteConnectionConfig;
import org.apache.calcite.linq4j.*;
import org.apache.calcite.linq4j.tree.Expression;
import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelOptTable;
import org.apache.calcite.prepare.Prepare;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.core.TableModify;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.schema.*;
import org.apache.calcite.schema.impl.AbstractTableQueryable;
import org.apache.calcite.schema.impl.ListTransientTable;
import org.apache.calcite.sql.SqlCall;
import org.apache.calcite.sql.SqlNode;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CustomTable implements ModifiableTable, QueryableTable, ScannableTable {

    Store store;
    public CustomTable(Store store)
    {
        this.store = store;
    }

    @Override
    public @Nullable Collection getModifiableCollection() {
        return null;
    }

    @Override
    public TableModify toModificationRel(RelOptCluster relOptCluster, RelOptTable relOptTable, Prepare.CatalogReader catalogReader, RelNode relNode, TableModify.Operation operation, @Nullable List<String> list, @Nullable List<RexNode> list1, boolean b) {
        return null;
    }

    @Override
    public <T> Queryable<T> asQueryable(QueryProvider queryProvider, SchemaPlus s, String tableName) {
        return new AbstractTableQueryable<T>(queryProvider, s, this, tableName) {
            @Override
            public Enumerator<T> enumerator() {
                return Linq4j.enumerator(Collections.emptyList());
            }
        };
    }

    @Override
    public Type getElementType() {
        return new ListTransientTable("table", null).getElementType();
    }

    @Override
    public Expression getExpression(SchemaPlus schemaPlus, String s, Class aClass) {
        return new ListTransientTable("table", null).getExpression(schemaPlus, s, aClass);
    }

    @Override
    public Enumerable<Object[]> scan(DataContext dataContext) {
        return new AbstractEnumerable<Object[]>() {
            @Override
            public Enumerator<Object[]> enumerator() {
                Linq4j.enumerator(store.numbers);
            }
        };
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory relDataTypeFactory) {
        return null;
    }

    @Override
    public Statistic getStatistic() {
        return Statistics.UNKNOWN;
    }

    @Override
    public Schema.TableType getJdbcTableType() {
        return Schema.TableType.TABLE;
    }

    @Override
    public boolean isRolledUp(String s) {
        return false;
    }

    @Override
    public boolean rolledUpColumnValidInsideAgg(String s, SqlCall sqlCall, @Nullable SqlNode sqlNode, @Nullable CalciteConnectionConfig calciteConnectionConfig) {
        return false;
    }
}
