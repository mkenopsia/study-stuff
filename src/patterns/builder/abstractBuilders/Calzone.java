package patterns.builder.abstractBuilders;

public class Calzone extends Pizza {
    private boolean isSauceInside = false;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean isSauceInside = false;

        public Builder sauceInside() {
            isSauceInside = true;
            return this;
        }

        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Calzone(Builder builder) {
        super(builder);
    }
}
