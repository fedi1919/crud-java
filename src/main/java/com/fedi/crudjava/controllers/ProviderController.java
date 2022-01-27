package com.fedi.crudjava.controllers;

import com.fedi.crudjava.entities.Provider;
import com.fedi.crudjava.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/provider/")
public class ProviderController {

    public static String providersDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images/providers";

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
    public String addProvider(@Valid Provider provider, BindingResult result,
                              @RequestParam("files") MultipartFile[] files) {
        if (result.hasErrors()) {
            return "provider/addProvider";
        }
        providerRepository.save(provider);
        return "redirect:list";
    }

    @GetMapping("delete/{id}")
    public String deleteProvider(@PathVariable("id") long idp, Model model) {  //@pathvarirable pour recuperer un parametre dans l'url


        //long id2 = 100L;

        Provider provider = providerRepository.findById(idp).orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + idp));

        System.out.println("suite du programme...");

        providerRepository.delete(provider);

       /*model.addAttribute("providers",
           providerRepository.findAll());
        return "provider/listProviders";*/

        return "redirect:../list";
    }

    @GetMapping("edit/{id}")
    public String showProviderFormToUpdate(@PathVariable("id") long
                                                   id, Model model) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));

        model.addAttribute("provider", provider);

        return "provider/updateProvider";
    }

    @PostMapping("update")
    public String updateProvider(@Valid Provider provider,
                                 BindingResult result, Model model) {


        providerRepository.save(provider);
        return"redirect:list";

    }
}



