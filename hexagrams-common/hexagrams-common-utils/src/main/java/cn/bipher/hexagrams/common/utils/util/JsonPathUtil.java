package cn.bipher.hexagrams.common.utils.util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

/**
 * jsonPath 工具类 参考 {@link JsonPath}
 *
 * @author Bipher
 * @version 1
 * @date 2022/12/30 13:45
 */
public class JsonPathUtil {

    private DocumentContext context;

    /**
     * @param json json
     */
    public JsonPathUtil(String json) {
        this.context = JsonPath.parse(json);
    }

    /**
     * 重载
     *
     * @param json json
     */
    public void reset(String json) {
        this.context = JsonPath.parse(json);
    }

    /**
     * 读 json path
     *
     * @param jsonPath json Path
     * @param <T>      T
     * @return T
     */
    public <T> T read(String jsonPath) {
        return context.read(jsonPath);
    }

    /**
     * 读 json path
     *
     * @param jsonPath json path
     * @param type     type
     * @param <T>      T
     * @return T
     */
    public <T> T read(String jsonPath, Class<T> type) {
        return context.read(jsonPath, type);
    }

    /**
     * @param jsonPath json path
     * @param type     type
     * @param filters  {@link Predicate}
     * @param <T>      T
     * @return T
     */
    public <T> T read(String jsonPath, Class<T> type, Predicate... filters) {
        return context.read(jsonPath, type, filters);
    }

    /**
     * Set the value a the given path
     *
     * @param path     path to set
     * @param newValue new value
     * @param filters  filters
     * @return json
     */
    public String set(String path, Object newValue, Predicate... filters) {
        return this.context.set(path, newValue, filters).jsonString();
    }

    /**
     * Set the value a the given path
     *
     * @param path     path to set
     * @param newValue new value
     * @return json
     */
    public String set(JsonPath path, Object newValue) {
        return this.context.set(path, newValue).jsonString();
    }

    /**
     * Add value to array
     *
     * <pre>
     * <code>
     * List<Integer> array = new ArrayList<Integer>(){{
     *      add(0);
     *      add(1);
     * }};
     *
     * JsonPath.parse(array).add("$", 2);
     *
     * assertThat(array).containsExactly(0,1,2);
     * </code>
     * </pre>
     *
     * @param path    path to array
     * @param value   value to add
     * @param filters filters
     * @return a document context
     */
    public String add(String path, Object value, Predicate... filters) {
        return this.context.add(path, value, filters).jsonString();
    }

    /**
     * Add value to array at the given path
     *
     * @param path  path to array
     * @param value value to add
     * @return a document context
     */
    public String add(JsonPath path, Object value) {
        return this.context.add(path, value).jsonString();
    }

    /**
     * Add or update the key with a the given value at the given path
     *
     * @param path    path to object
     * @param key     key to add
     * @param value   value of key
     * @param filters filters
     * @return a document context
     */
    public String put(String path, String key, Object value, Predicate... filters) {
        return this.context.put(path, key, value, filters).jsonString();
    }

    /**
     * Add or update the key with a the given value at the given path
     *
     * @param path  path to array
     * @param key   key to add
     * @param value value of key
     * @return a document context
     */
    public String put(JsonPath path, String key, Object value) {
        return this.context.put(path, key, value).jsonString();
    }

    /**
     * Renames the last key element of a given path.
     *
     * @param path       The path to the old key. Should be resolved to a map
     *                   or an array including map items.
     * @param oldKeyName The old key name.
     * @param newKeyName The new key name.
     * @param filters    filters.
     * @return a document content.
     */
    public String renameKey(String path, String oldKeyName, String newKeyName, Predicate... filters) {
        return this.context.renameKey(path, oldKeyName, newKeyName, filters).jsonString();
    }

    /**
     * Renames the last key element of a given path.
     *
     * @param path       The path to the old key. Should be resolved to a map
     *                   or an array including map items.
     * @param oldKeyName The old key name.
     * @param newKeyName The new key name.
     * @return a document content.
     */
    public String renameKey(JsonPath path, String oldKeyName, String newKeyName) {
        return this.context.renameKey(path, oldKeyName, newKeyName).jsonString();
    }

    /**
     * Deletes the given path
     *
     * @param path    path to delete
     * @param filters filters
     * @return a document context
     */
    public String delete(String path, Predicate... filters) {
        return this.context.delete(path, filters).jsonString();
    }

    /**
     * Deletes the given path
     *
     * @param path path to delete
     * @return a document context
     */
    public String delete(JsonPath path) {
        return this.context.delete(path).jsonString();
    }
}