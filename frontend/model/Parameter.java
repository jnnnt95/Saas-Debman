package model;

public class Parameter {
    private String name;
    private String content;
    
    public Parameter(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Content: " + content;
    }
}
