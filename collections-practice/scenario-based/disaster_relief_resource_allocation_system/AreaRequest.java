import java.util.HashMap;
import java.util.Map;

class AreaRequest {
    String areaName;
    int population;
    Map<String, Integer> requestedResources;

    public AreaRequest(String areaName, int population) {
        this.areaName = areaName;
        this.population = population;
        this.requestedResources = new HashMap<>();
    }

    public void addResourceRequest(String item, int quantity) {
        requestedResources.put(item, quantity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(areaName).append(" | Population: ").append(population).append(" | Needs: ");
        for (Map.Entry<String, Integer> entry : requestedResources.entrySet()) {
            sb.append(entry.getKey()).append("(").append(entry.getValue()).append(") ");
        }
        return sb.toString();
    }
}
