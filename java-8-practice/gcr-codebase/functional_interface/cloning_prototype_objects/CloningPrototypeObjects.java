package cloning_prototype_objects;

public class CloningPrototypeObjects {

    public static void main(String[] args) {
        PrototypeConfig baseConfig = new PrototypeConfig("Production", 150, true);
        PrototypeConfig clonedConfig = baseConfig.clone();
        clonedConfig.setEnvName("Staging");

        System.out.println("Cloning Prototype Objects");
        System.out.println("=========================");

        System.out.println("Base:   " + baseConfig);
        System.out.println("Cloned: " + clonedConfig);
    }
}
