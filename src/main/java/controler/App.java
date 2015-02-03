package controler;

import java.util.Iterator;
import java.util.List;

import model.dao.AparcamientosPublicosDAO;
import model.vo.AparcamientosPublicosVO;

public class App {

	public static void main(String[] args) {

		AparcamientosPublicosDAO apDAO = new AparcamientosPublicosDAO();
		List<AparcamientosPublicosVO> aparcamientos = apDAO
				.getListadoAparcamientos();

		if (aparcamientos != null) {

			System.out
					.println("-------------Lista de Aparcamientos--------------");

			Iterator<AparcamientosPublicosVO> it = aparcamientos.iterator();
			while (it.hasNext()) {
				AparcamientosPublicosVO aparcamiento = it.next();
				System.out.println(aparcamiento.toString());
			}
		}
	}

}
