package builder;

public class BuilderPluginTest {
    public static void main(String[] args) {
        Car car = Car.builder()<caret>.build();
    }
    static class Car {
        String name;
        int year;
        private Car(Builder container) {
            this.name = container.name;
            this.year = container.year;
        }
        public static Builder builder() {
            return new Builder();
        }
        static class Builder {
            private String name;
            private int year;
            public Builder setName(String name) {
                this.name = name;
                return this;
            }
            public Builder setYear(int year) {
                this.year = year;
                return this;
            }
            public Car build() {
                return new Car(this);
            }
        }
    }
}
