package com.example.demo.controllers;

import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
public class OrdersController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value="/orders/create", method = RequestMethod.GET)
    public ModelAndView newUser(){
        ModelAndView modelAndView = new ModelAndView();
        Orders orders=new Orders();
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("listProduct", productRepository.findAll());

        modelAndView.setViewName("orders/create");
        return  modelAndView;
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.POST)
    public ModelAndView saveTipZavedinia(@Valid Orders orders, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (bindingResult.hasErrors()) {
                orders.getProduct().setKolvo(orders.getQty());
                modelAndView.setViewName("orders/create");
            } else {
                if (orders.getProduct().isNalichie()==true) {
                    if (orders.getQty()<=orders.getProduct().getKolvo()){
                    orders.getProduct().addKolvo(orders.getQty());
                    if (orders.getProduct().getKolvo()==0){orders.getProduct().setNalichie(false);}
                    orders.getProduct().skiddkaResh(orders.getProduct().getSkidka());
                    orders.getProduct().obshiySena(orders.getQty());
                    orders.setSum((int) (orders.getProduct().getObshiy()*orders.getQty()));
                    orderRepository.save(orders);}
                    modelAndView.setViewName("redirect:/orders/index");
                } else { modelAndView.setViewName("redirect:/orders/index");}
            }
            return modelAndView;
        } catch (Exception exp){
            modelAndView.addObject("Error", exp.getMessage());
            modelAndView.addObject("orders", orders);
            modelAndView.addObject("listProduct", productRepository.findAll());
            modelAndView.setViewName("orders/create");
            return  modelAndView;
        }
    }
    @RequestMapping(value={"orders/index"}, method = RequestMethod.GET)
    public ModelAndView Index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", orderRepository.findAll());
        modelAndView.setViewName("orders/index");
        return modelAndView;
    }
    @RequestMapping(value={"history/index"}, method = RequestMethod.GET)
    public ModelAndView history(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", orderRepository.findAll());
        modelAndView.setViewName("history/index");
        return modelAndView;
    }


}
