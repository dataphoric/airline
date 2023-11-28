package edu.neu.demo.utils;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class JsonValidator {

    // 修改：移除特定于 crew 的 schema 成员变量

    public JsonValidator() {
        // 构造函数不再加载特定 schema
    }

    // 重载 validate 方法以接收 schema 路径
    public boolean validate(String schemaPath, JSONObject jsonObject) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(schemaPath);
            JSONObject schemaJson = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(schemaJson);
            schema.validate(jsonObject); // 使用传入的 schema 进行验证
        } catch (Exception exception) {
            System.out.println("Validation Exception: " + exception.getMessage());
            return false;
        }
        return true;
    }

    // 重载 validate 方法以接收 Schema 对象
    public boolean validate(Schema schema, JSONObject jsonObject) {
        try {
            schema.validate(jsonObject); // 使用传入的 schema 进行验证
        } catch (Exception exception) {
            System.out.println("Validation Exception: " + exception.getMessage());
            return false;
        }
        return true;
    }

}
