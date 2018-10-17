package com.gametricsapp.gaMetrics;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;

@Controller
public class GametricsController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/cpu")
	public String cpus(Model model) {

		Components components = JSensors.get.components();
		String totalCPU = "";
		List<Cpu> cpus = components.cpus;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		totalCPU = gson.toJson(cpus.get(0).sensors.loads.get(8).value);
		model.addAttribute("totalCPU", totalCPU);
		return "cpu";
	}
	
	@GetMapping("/ram")
	public String ram(Model model) {

		Components components = JSensors.get.components();
		String ramLoad = "";
		List<Cpu> cpus = components.cpus;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ramLoad = gson.toJson(cpus.get(0).sensors.loads.get(9).value);
		model.addAttribute("ramLoad", ramLoad);
		return "ram";
	}
	
	@GetMapping("/gpu")
	public String gpu(Model model) {

		Components components = JSensors.get.components();
		String totalGPU = "";
		totalGPU = components.gpus.get(0).sensors.loads.get(3).value+"";
		model.addAttribute("totalGPU", totalGPU);
		return "gpu";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		return "dashboard";
	}

}
