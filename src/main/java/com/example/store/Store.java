package com.example.store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Devices;

@Controller
public class Store {
	private Devices result;
	private ModelAndView mv ;

	@Autowired
	RestTemplate restTemplate;


	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/{brandName}")
	public ModelAndView samsung(@PathVariable("brandName") String brandName) {
		mv= new ModelAndView("viewDevices");
		switch(brandName.toLowerCase())
		{
		case "samsung":
			result= restTemplate.getForObject("http://SAMSUNG/samsung/devices", Devices.class);
            mv.addObject("devices", result) ;
            return mv;
	
		case "apple":
		    result = restTemplate.getForObject("http://APPLE/apple/devices", Devices.class);
            mv.addObject("devices", result) ;
            return mv;
            
            default :
            	return  new ModelAndView("redirect:/error");
	}
}

}