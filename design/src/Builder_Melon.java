public class Builder_Melon {
    String name;
    int age;
    String color;
    Builder_Melon(Builder builder){
        this.name=builder.name;
        this.age=builder.age;
        this.color=builder.color;
    }
    public String toString(){
        return "My name is "+this.name+"\nAge is "+age+"\nColor is "+color;
    }
    public static class Builder{
        private String name;
        private int age;
        private String color;
        public  Builder setName(String name){
            this.name=name;
            return this;
        }
        public Builder setAge(int age){
            this.age=age;
            return this;
        }
        public Builder setColor(String color){
            this.color=color;
            return this;
        }
        public Builder_Melon build(){
            return new Builder_Melon(this);
        }
    }

    public static void main(String[] args) {
        Builder_Melon builder_melon=new Builder_Melon.Builder().setName("Melon King")
                .setAge(99)
                .setColor("green").build();
        System.out.println(builder_melon.toString());
    }
}
