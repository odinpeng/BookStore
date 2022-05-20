package pojo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/*购物车类*/
public class Cart {
    /*items  key:商品编号， value:商品信息 */
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();

    public Cart(Map<Integer,CartItem> items) {
        this.items = items;
    }

    public Cart() {}

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        /*cartItem.getId()：获取传入商品项的id     items.get(id)：通过id找集合中对应的商品项*/
        CartItem item = items.get(cartItem.getId());
        if (item==null){
            /*如果购物车中不含此商品项，则直接向items(商品项)集合中添加商品*/
            items.put(cartItem.getId(),cartItem);
        }else{
            /*如果购物车中已包含此商品项，则商品数量和商品价格更新*/
            item.setCount(cartItem.getCount()+1); //数量+1
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); //商品单价x商品总数
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量,并修改累计价格
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count){
        /*获取集合中id对应的商品项*/
        CartItem cartItem = items.get(id);
        if (cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    /**
     * 获取商品总数量
     * @return
     */
    public Integer getTotalCount() {
        Integer totalCount=0;
        /*遍历集合*/
        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
           totalCount += entry.getValue().getCount(); //获取每个商品项的数量 并累加
        }
        return totalCount;
    }

    /**
     * 获取商品总价格
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer,CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

}
