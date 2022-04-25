package com.company.Furniture.services;

import com.company.Furniture.components.Furniture.*;
import com.company.Furniture.components.shape.RectangularShape;
import com.company.Furniture.components.shape.RoundShape;
import com.company.Furniture.components.shape.Shaped;
import com.company.Furniture.components.types.TypeColor;
import com.company.Furniture.components.types.TypeMaterial;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class DataParserJSON implements DataParserService {

    private JSONObject parseJSON(String orderName) {
        Object obj = null;

        try {
            obj = new JSONParser().parse(new FileReader(orderName));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return (JSONObject) obj;
    }

    private Shaped unpackShape(String shapeName, ArrayList<Double> size) {
        if(Objects.equals(shapeName, "RECTANGULAR")){
            return RectangularShape.builder()
                    .width(size.get(0))
                    .depth(size.get(1))
                    .height(size.get(2))
                    .build();
        }
        if(Objects.equals(shapeName, "ROUND")){
            return RoundShape.builder()
                    .height(size.get(0))
                    .radius(size.get(1))
                    .build();
        }
        throw new IllegalArgumentException("Shape type is null");
    }

    private TypeColor unpackColor(String colorName){
        return TypeColor.valueOf(colorName);
    }

    private TypeMaterial unpackMaterial(String materialName){
        return TypeMaterial.valueOf(materialName);
    }

    private Component unpackFillerFields(JSONObject object){
        return Filler.builder()
                .material(this.unpackMaterial((String) object.get("material")))
                .volume((Long) object.get("volume"))
                .build();
    }

    private ArrayList<Component> unpackUnitFields(JSONArray array){
        Iterator iterator = array.iterator();
        ArrayList<Component> elements = new ArrayList<>();
        while(iterator.hasNext()){
            JSONObject field = (JSONObject) iterator.next();
            String[] sizeArray = field.get("size").toString().split(" ");
            ArrayList<Double> size = new ArrayList<>();
            for (String s : sizeArray) {
                size.add(Double.parseDouble(s));
            }
            Component leg = Unit.builder()
                    .shape(this.unpackShape((String) field.get("shape"), size))
                    .material(this.unpackMaterial((String) field.get("material")))
                    .color(this.unpackColor((String) field.get("color")))
                    .build();
            elements.add(leg);
        }
        return elements;
    }

    @Override
    public Component unpackingChairOrder(String orderName){
        JSONObject order = this.parseJSON(orderName);

        JSONArray array = (JSONArray) order.get("base");
        ArrayList<Component> elements = new ArrayList<>(this.unpackUnitFields(array));
        array = (JSONArray) order.get("legs");
        elements.addAll(this.unpackUnitFields(array));
        array = (JSONArray) order.get("back");
        elements.addAll(this.unpackUnitFields(array));

        return Chair.builder().elements(elements).build();
    }


    @Override
    public Component unpackingTableOrder(String orderName) {
        JSONObject order = this.parseJSON(orderName);
        JSONArray array = (JSONArray) order.get("base");
        ArrayList<Component> elements = new ArrayList<>(this.unpackUnitFields(array));
        array = (JSONArray) order.get("legs");
        elements.addAll(this.unpackUnitFields(array));

        return Table.builder().elements(elements).build();
    }

    @Override
    public Component unpackingSofaOrder(String orderName) {
        JSONObject order = this.parseJSON(orderName);
        JSONArray array = (JSONArray) order.get("frame");
        ArrayList<Component> elements = new ArrayList<>(this.unpackUnitFields(array));
        elements.add(this.unpackFillerFields((JSONObject) order.get("filler")));

        return Sofa.builder().elements(elements).build();
    }

}
