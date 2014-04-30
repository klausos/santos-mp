package br.com.lossantos.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import br.com.lossantos.dao.CityDao;
import br.com.lossantos.model.City;

@Service
public class CitiesSlugGenerator {

	@Autowired
	private CityDao cityDao;

	public static void main(String[] args) {
		final AbstractApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/spring/root-context.xml",
				"src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
				"src/main/webapp/WEB-INF/spring/appServlet/app-context.xml");

		CitiesSlugGenerator csg = context.getBean(CitiesSlugGenerator.class);
		csg.generate();

		context.close();
	}

	private void generate() {
		List<City> cities = cityDao.findAll();
		for (City city : cities) {
			// just to make the entity dirty
			city.setSlug(city.getName());
			cityDao.add(city);
		}
	}
}
