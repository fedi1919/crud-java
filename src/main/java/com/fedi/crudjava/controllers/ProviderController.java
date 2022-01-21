package com.fedi.crudjava.controllers;

import com.fedi.crudjava.entities.Provider;
import com.fedi.crudjava.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/provider/")
public class ProviderController {

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @GetMapping("list")
    //@ResponseBody
    public String listProviders(Model model) {

        List<Provider> lp = (List<Provider>) providerRepository.findAll();
        if (lp.size() == 0) lp = null;
        model.addAttribute("providers", lp);

        return "provider/listProviders";

        //List<Provider> lp =
        //(List<Provider>)providerRepository.findAll();
        //System.out.println(lp);

        //return "Nombre de fournisseur = " + lp.size();
    }

    @GetMapping("add")
    public String showAddProviderForm(Model model) {
        Provider provider = new Provider();// object dont la valeur des attributs par defaut
        model.addAttribute("provider", provider);
        return "provider/addProvider";
    }

    @PostMapping("add")
    public String addProvider(@Valid Provider provider, BindingResult result) {
        if (result.hasErrors()) {
            return "provider/addProvider";
        }
        providerRepository.save(provider);
        return "redirect:list";
    }

}
