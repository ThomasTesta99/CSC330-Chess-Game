package Items;

public abstract class DefinedItem implements Item{
    private String itemName;
    private int itemCost;
    private boolean isUsed;

    public DefinedItem(String name, int cost){
        setItemName(name);
        setItemCost(cost);
        setIsUsed(false);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCost() {
        return itemCost;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    public boolean isUsed(){
        return isUsed;
    }

    public void setIsUsed(boolean i){
        isUsed = i;
    }
}
