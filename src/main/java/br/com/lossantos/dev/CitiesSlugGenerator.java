package br.com.lossantos.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lossantos.dao.CityDao;
import br.com.lossantos.model.City;

@Service
public class CitiesSlugGenerator {

	@Autowired
	private CityDao cityDao;

	public static void main(String[] args) {
		// TODO finish things here
		// final AbstractApplicationContext context = new
		// FileSystemXmlApplicationContext(
		// "src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		// "src/main/webapp/WEB-INF/spring/appServlet/app-context.xml");
		//
		// CitiesSlugGenerator csg = context.getBean(CitiesSlugGenerator.class);
		// csg.generate();
		//
		// context.close();
	}

	@Transactional
	private void generate() {
		List<City> cities = cityDao.findAll();
		for (City city : cities) {
			cityDao.add(city);
		}
	}

}
