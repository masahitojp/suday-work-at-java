package me.masahito.json.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

// Note: can use getters/setters as well; here we just use public fields directly:
public class MyValue {
    public void setName(String name) {
        this._name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonProperty("name")
    public String getName() {
        return _name;
    }

    public int getAge() {
        return age;
    }

    protected String _name;
    protected int age;
    // NOTE: if using getters/setters, can keep fields `protected` or `private`

    @Override
    public String toString() {
        return String.format("name: %s, age %d", _name, age);
    }
}