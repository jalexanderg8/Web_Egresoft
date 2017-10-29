package bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.AdminDao;
import entidades.Administrador;
import mensajes.MessagesView;

@ManagedBean(name = "adminBean", eager = true)
@SessionScoped
public class AdminBean implements Serializable {

	private Administrador admin = new Administrador();
	private List<Administrador> listaAdministradores;
	private AdminDao aDao;
	private MessagesView msj = new MessagesView();

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public List<Administrador> getListaAdministradores() {

		aDao = new AdminDao();
		listaAdministradores = aDao.listaAdministradores();

		return listaAdministradores;
	}

	public void setListaAdministradores(List<Administrador> listaAdministradores) {

		this.listaAdministradores = listaAdministradores;
	}

	public void nuevoAdministrador() {

		try {
			aDao = new AdminDao();
			aDao.registrar(admin);
			admin = new Administrador();

		} catch (Exception e) {

		}

	}

	public void editarAdministrador() {

		try {
			aDao = new AdminDao();
			aDao.editar(admin);
			admin = new Administrador();

		} catch (Exception e) {

		}

	}

	public void eliminarAdministrador() {

		try {
			aDao = new AdminDao();
			aDao.eliminar(admin);
			admin = new Administrador();

		} catch (Exception e) {

		}

	}

	public AdminDao getaDao() {
		return aDao;
	}

	public void setaDao(AdminDao aDao) {
		this.aDao = aDao;
	}

	public MessagesView getMsj() {
		return msj;
	}

	public void setMsj(MessagesView msj) {
		this.msj = msj;
	}

}
