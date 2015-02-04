package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.vo.AparcamientosPublicosVO;
import model.vo.Punto;

public class AparcamientosPublicosDAO {

	public static Logger logger = Logger
			.getLogger(AparcamientosPublicosDAO.class.getName());

	// Listado de aparcamientos

	public List<AparcamientosPublicosVO> getListadoAparcamientos()
			throws ClassNotFoundException, NamingException, SQLException {
		List<AparcamientosPublicosVO> aparcamientos = new ArrayList<AparcamientosPublicosVO>();
		String query = "select ID,LASTUPDATED,ICON,TITLE,HORARIO,ACCESOPEATON,ACCESOS,ACCESOVEHICULO,COORDX,COORDY"
				+ " from EQ4_APARCA";

		// String query2 = "select * from EQ4_APARCA WHERE ACCESOPEATON = ?";
		// String query3 =
		// "INSERT INTO EQ4_APARCA (ID,LASTUPDATED,ICON,TITLE,HORARIO,ACCESOPEATON,ACCESOS,ACCESOVEHICULO,COORDX,COORDY) VALUES ("
		// + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		// String query4 = "INSERT INTO EQ4_APARCA (ID) VALUES (?)";

		Statement st = null;
		ResultSet rs = null;
	
		ConsultaOrcl co = new ConsultaOrcl();
		Connection conection = co.conexion();
		

		
		// transacciones
		/*
		 * try{ conection.setAutoCommit(false); for(int i = 1;i <= 3; i++){ int
		 * id = (int) Math.floor(Math.random()*15+5); st =
		 * conection.prepareStatement(query4); st.setInt(1, id); int m =
		 * st.executeUpdate();
		 * System.out.println("Numero de lineas modificadas= " + m);
		 * System.out.println(id); if ((i < 1) && !((id%2) == 0)){
		 * conection.commit(); System.out.println("Hago commit");
		 * 
		 * }else if((i < 1) && ((id%2) == 0)){
		 * System.out.println("Hago rollback"); conection.rollback(); }
		 * 
		 * } conection.setAutoCommit(true);
		 * 
		 * }catch(SQLException e){ logger.log(Level.SEVERE, e.getMessage());
		 * try{ conection.rollback(); }catch(Exception e2){
		 * logger.log(Level.SEVERE, e2.getMessage()); }
		 * }catch(NullPointerException ex){ logger.log(Level.SEVERE,
		 * ex.getMessage()); try{ conection.rollback(); }catch(Exception e2){
		 * logger.log(Level.SEVERE, e2.getMessage()); } }
		 */

		// CallableStatement
		/*
		 * try{ CallableStatement cst =
		 * conection.prepareCall("{?=call NUMERO}"); cst.registerOutParameter(1,
		 * java.sql.Types.TINYINT); //cst.setInt(2, 1); cst.executeQuery(); int
		 * res = cst.getInt(1);
		 * System.out.println("Resultado callableStatement= " + res);
		 * }catch(NullPointerException e){ logger.log(Level.SEVERE,
		 * e.getMessage()); }catch(SQLException ex){ logger.log(Level.SEVERE,
		 * ex.getMessage()); }
		 */

		try {
			st = conection.createStatement();
			rs = st.executeQuery(query);
			Punto punto = null;

			while (rs.next()) {

				int id = rs.getInt(1);
				Date fecha = rs.getDate(2);
				String icon = rs.getString(3);
				String title = rs.getString(4);
				String horario = rs.getString(5);
				String accesoPeaton = rs.getString(6);
				String accesos = rs.getString(7);
				String accesoVehiculo = rs.getString(8);

				int x = rs.getInt(9);
				int y = rs.getInt(10);

				punto = new Punto(x, y);

				AparcamientosPublicosVO aparcamiento = new AparcamientosPublicosVO(
						punto, horario, title, icon, accesos, fecha,
						accesoPeaton, accesoVehiculo, id);

				aparcamientos.add(aparcamiento);
			}

		}

		catch (NullPointerException e) {

			logger.log(Level.SEVERE, e.getMessage());
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, ex.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {

				}
			}
		}

		return aparcamientos;
	}

	public AparcamientosPublicosVO detalleAparcamientos(int id)
			throws ClassNotFoundException, NamingException, SQLException {

		AparcamientosPublicosVO aparcamiento = null;

		String query = "select ID,LASTUPDATED,ICON,TITLE,HORARIO,ACCESOPEATON,ACCESOS,ACCESOVEHICULO,COORDX,COORDY"
				+ " from EQ4_APARCA WHERE ID = " + id;

		Statement st = null;
		ResultSet rs = null;
			
		ConsultaOrcl co = new ConsultaOrcl();
		Connection conection = co.conexion();
		try {
			st = conection.createStatement();
			rs = st.executeQuery(query);
			Punto punto = null;

			while (rs.next()) {

				int ids = rs.getInt(1);
				Date fecha = rs.getDate(2);
				String icon = rs.getString(3);
				String title = rs.getString(4);
				String horario = rs.getString(5);
				String accesoPeaton = rs.getString(6);
				String accesos = rs.getString(7);
				String accesoVehiculo = rs.getString(8);

				int x = rs.getInt(9);
				int y = rs.getInt(10);

				punto = new Punto(x, y);

				aparcamiento = new AparcamientosPublicosVO(punto, horario,
						title, icon, accesos, fecha, accesoPeaton,
						accesoVehiculo, id);

			}

		}

		catch (NullPointerException e) {

			logger.log(Level.SEVERE, e.getMessage());
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, ex.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {

				}
			}
		}

		return aparcamiento;
	}

}
