import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.util.Map;

public class CustomSchema extends AbstractSchema {

    Store store;
    public CustomSchema(Store store) { this.store = store; }

    @Override
    public Map<String, Table> getTableMap() {
        return Map.of("table", new CustomTable(this.store));
    }
}
