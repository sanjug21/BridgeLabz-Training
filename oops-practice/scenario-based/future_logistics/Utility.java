public class Utility {

    public GoodsTransport parseDetails(String input){
        GoodsTransport transportType;
        String[] details = input.trim().split(":");
        if(details[3].equalsIgnoreCase("BrickTransport")){
            transportType = new BrickTransport(details[0], details[1], Integer.parseInt(details[2]),
                    Float.parseFloat(details[4]), Integer.parseInt(details[5]), Float.parseFloat(details[6]));
        } else if(details[3].equalsIgnoreCase("TimberTransport")){
            transportType = new TimberTransport(details[0], details[1], Integer.parseInt(details[2]),
                    Float.parseFloat(details[4]), Integer.parseInt(details[5]), details[6],
                    Float.parseFloat(details[7]));
        }else{
            transportType=null;
        }
        return transportType;
    }

    public boolean validateTransportId(String transportId){
        String regex = "^RTS[0-9]{3}[A-Z]$";
        if(transportId.matches(regex)){
            return true;
        } else {
            return false;
        }
    }

    public String findObjectType(GoodsTransport goodsTransport){
        if(goodsTransport instanceof TimberTransport){
            return "TimberTransport";
        }
        if(goodsTransport instanceof BrickTransport){
            return "BrickTransport";
        }
        return "InvalidTransport";
    }


}
