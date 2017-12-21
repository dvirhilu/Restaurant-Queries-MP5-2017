package ca.ece.ubc.cpen221.mp5.Query;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Cynzy on 2017-11-25.
 */
public class MP5QueryListenerGenerateList extends MP5QueryParserBaseListener {

    private List<String> categories;
    private List<String> locations;
    private List<String> names;
    private List<Integer> rating;
    private List<Integer> price;

    public MP5QueryListenerGenerateList(List<String> categories, List<String> locations, List<String> names, List<Integer> rating, List<Integer> price) {
        this.categories = categories;
        this.locations = locations;
        this.names = names;
        this.rating = rating;
        this.price = price;
    }

    @Override
    public void exitCategory(MP5QueryParser.CategoryContext ctx) {
        TerminalNode token = ctx.STR();
        this.categories.add(token.getText());
    }

    @Override
    public void exitIn(MP5QueryParser.InContext ctx) {
        TerminalNode token = ctx.STR();
        Set<String> neighborhoods = new HashSet<>();
        neighborhoods.add(token.getText());
        this.locations.addAll(neighborhoods);
    }

    @Override
    public void exitName(MP5QueryParser.NameContext ctx) {
        TerminalNode token = ctx.STR();
        this.names.add(token.getText());
    }

    @Override
    public void exitPrice (MP5QueryParser.PriceContext ctx) {
        TerminalNode token = ctx.NUM();
        MP5QueryParser.IneqContext ineq = ctx.ineq();
        String cond = ineq.getText();
        int price = Integer.parseInt(token.getText());


        int maxPrice = 5;
        if (cond.equals("<")){
            for (int i = 1; i < price; i++){
                this.price.add(i);
            }
        } else if (cond.equals("<=")){
            for (int i = 1; i <= price; i++){
                this.price.add(i);
            }
        } else if (cond.equals("=")){
            this.price.add(price);
        } else if (cond.equals(">")){
            for (int i = maxPrice; maxPrice > price; i--){
                this.price.add(i);
            }
        } else if (cond.equals(">=")){
            for (int i = maxPrice; maxPrice >= price; i--){
                this.price.add(i);
            }
        } else {
            throw ctx.exception;
        }


    }

    @Override
    public void exitRating(MP5QueryParser.RatingContext ctx) {
        TerminalNode token = ctx.NUM();
        MP5QueryParser.IneqContext ineq = ctx.ineq();
        String cond = ineq.getText();
        int rating = Integer.parseInt(token.getText());

        int maxRating = 5;
        if (cond.equals("<")){
            for (int i = 1; i < maxRating; i++){
                this.rating.add(i);
            }
        } else if (cond.equals("<=")){
            for (int i = 1; i <= maxRating; i++){
                this.rating.add(i);
            }
        } else if (cond.equals("=")){
            this.price.add(rating);
        } else if (cond.equals(">")){
            for (int i = maxRating; maxRating > rating; i--){
                this.rating.add(i);
            }
        } else if (cond.equals(">=")){
            for (int i = maxRating; maxRating >= rating; i--){
                this.rating.add(i);
            }
        }


    }
}
