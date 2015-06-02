import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;

public class FlightPlanner extends ConsoleProgram {
	
	private HashMap<String, Route> routes = new HashMap<String, Route>();
	private ArrayList<String> plan = new ArrayList<String>(); 
	
	public void run() {
		loadRoutes();	
		welcome();		
		planRoute();
		printRoute();
	}

	private void welcome() {
		println("Welcome to Flight Planner!");
		println("Here's a list of all the cities in our database:");
		for (String city : routes.keySet()) {
			println(" " + city);
		}
		println("Let's plan a round-trip route!");
	}

	private void planRoute() {
		String starting = readLine("Enter the starting city: ");
		String city = starting;
		plan.add(city);
		do {
			Route route = routes.get(city);
			println("From " + city + " you can fly to:");
			Iterator<String> destinations = route.getDestinations();
			while (destinations.hasNext()) {
				println(" " + destinations.next());
			}
			String newCity = readLine("Where do you want to go from " + city + "? ");
			destinations = route.getDestinations();
			while (destinations.hasNext()) {
				if (newCity.equals(destinations.next())) {
					city = newCity;
					break;
				}
			}
			if (!newCity.equals(city)) {
				println("You can't get to that city by a direct flight.");
				continue;
			} else {
				plan.add(city);
			}
		} while (!city.equals(starting));
	}

	private void printRoute() {
		String str = "";
		for (int i = 0; i < plan.size(); i++) {
			str += plan.get(i) + " -> ";
		}
		str += plan.get(0);
		println("The route you've chosen is:");
		println(str);
	}
	
	private void loadRoutes() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("flights.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				line = line.trim();
				if (line.equals(""))
					continue; // skip blank lines
				parseRoute(line);
			}
			rd.close();
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
	
	private void parseRoute(String line) {
		String separator = " -> ";
		String origin = line.substring(0, line.indexOf(separator));
		String destiny = line.substring(line.indexOf(separator) + separator.length());
		
		Route route;
		if (routes.containsKey(origin)) {
			route = routes.get(origin);
		} else {
			route = new Route(origin);
			routes.put(origin, route);
		}
		route.addDestination(destiny);
	}
	
}

class Route {
	private ArrayList<String> destinations;
	private String origin;
	
	public Route(String newOrigin) {
		origin = newOrigin;
		destinations = new ArrayList<String>();
	}
	
	public void addDestination(String city) {
		destinations.add(city);
	}
	
	public Iterator<String> getDestinations() {
		return destinations.iterator();
	}
}
