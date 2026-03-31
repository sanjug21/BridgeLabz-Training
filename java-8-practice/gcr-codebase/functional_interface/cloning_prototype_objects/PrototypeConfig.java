package cloning_prototype_objects;

class PrototypeConfig implements Cloneable {
    private String envName;
    private int maxConnections;
    private boolean cacheEnabled;

    public PrototypeConfig(String envName, int maxConnections, boolean cacheEnabled) {
        this.envName = envName;
        this.maxConnections = maxConnections;
        this.cacheEnabled = cacheEnabled;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    @Override
    public PrototypeConfig clone() {
        try {
            return (PrototypeConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Cloning not supported", e);
        }
    }

    @Override
    public String toString() {
        return "Config[Env=" + envName + ", MaxConn=" + maxConnections + ", Cache=" + cacheEnabled + "]";
    }
}
