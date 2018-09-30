package com.gametricsapp.gaMetrics;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Fan;
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

	@GetMapping("/cpus")
	public String cpus(Model model) {

		Components components = JSensors.get.components();
		String output = "";
		List<Cpu> cpus = components.cpus;
		if (cpus != null) {
			for (final Cpu cpu : cpus) {
				output += cpu.name + "\n";
				if (cpu.sensors != null) {
					output += "Sensors: \n";

					// Print temperatures
					List<Temperature> temps = cpu.sensors.temperatures;
					for (final Temperature temp : temps) {
						output += temp.name + ": " + temp.value + " C\n";
					}
					
					List<Load> loads = cpu.sensors.loads;
					for (final Load load : loads) {
						output += load.name + ": " + load.value;
					}
				}
			}
		}

		model.addAttribute("cpunames", output);
		return "cpus";
	}

}
