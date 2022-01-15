package com.jamscoco.util;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("status", 200);
        put("msg", "成功");
    }

    public static R error() {
        return error(500, "发生错误");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int status, String msg) {
        R r = new R();
        r.put("status", status);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
