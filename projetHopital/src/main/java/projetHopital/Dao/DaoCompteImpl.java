package projetHopital.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projetHopital.model.Compte;
import projetHopital.model.Medecin;
import projetHopital.model.Secretaire;
import projetHopital.Dao.Closer;
import projetHopital.Dao.Context;

public class DaoCompteImpl implements DaoCompte {

	@Override
	public void insert(Compte compte) {

		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection()
					.prepareStatement("INSERT INTO compte (compte_login, compte_password, compte_type) values(?,?,?)"
							,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, compte.getLogin());
			ps.setString(2, compte.getPassword());
			if (compte instanceof Secretaire) {
				ps.setString(3, "S");
			} else {
				ps.setString(3, "M");
			}
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				compte.setCompte_id(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
	}

	@Override
	public void update(Compte compte) {

		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection()
					.prepareStatement("UPDATE compte SET compte_login=?, compte_password=?, compte_type=? WHERE id=?");
			ps.setString(1, compte.getLogin());
			ps.setString(2, compte.getPassword());
			if (compte instanceof Secretaire) {
				ps.setString(3, "S");
			} else {
				ps.setString(3, "M");
			}
			ps.setInt(4, compte.getCompte_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
	}

	@Override
	public void delete(Compte compte) {
		deleteByKey(compte.getCompte_id());
	}

	@Override
	public void deleteByKey(Integer key) {
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement("delete from compte where id=?");
			ps.setInt(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
	}

	private Secretaire getSecretaire(ResultSet rs) throws SQLException {
		// @formatter:off
		return new Secretaire(
						rs.getInt("id"), 
						rs.getString("compte_login"), 
						rs.getString("compte_password"));
		// @formatter:on

	}

	private Medecin getMedecin(ResultSet rs) throws SQLException {
		// @formatter:off
		return new Medecin(
						rs.getInt("id"), 
						rs.getString("compte_login"), 
						rs.getString("compte_password"));
		// @formatter:on

	}

	@Override
	public Compte findByKey(Integer key) {
		Compte compte = null;
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement("select * from compte where id=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("compte_type").equals("S")) {
					// on va remonter une secrétaire
					compte = getSecretaire(rs);
				} else {
					// on remonte un médecin
					compte = getMedecin(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		return compte;
	}

	@Override
	public List<Compte> findAll() {
		List<Compte> comptes = new ArrayList<>();
		Compte compte = null;
		Statement st = null;
		try {
			st = Context.getContext().getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM compte");
			while (rs.next()) {
				if (rs.getString("compte_type").equals("S")) {
					compte = getSecretaire(rs);
				} else {
					compte = getMedecin(rs);
				}
				comptes.add(compte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(st);
			Context.destroy();
		}
		return comptes;
	}

	@Override
	public List<Secretaire> findAllSecretaire() {
		List<Secretaire> secretaires = new ArrayList<>();
		Statement st = null;
		try {
			st = Context.getContext().getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM compte where compte_type='S'");
			while (rs.next()) {
				secretaires.add(getSecretaire(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(st);
			Context.destroy();
		}
		return secretaires;
	}

	@Override
	public List<Medecin> findAllMedecin() {
		List<Medecin> medecins = new ArrayList<>();
		Statement st = null;
		try {
			st = Context.getContext().getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM compte where compte_type='M'");
			while (rs.next()) {
				medecins.add(getMedecin(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(st);
			Context.destroy();
		}
		return medecins;
	}

	@Override
	public Secretaire findSecretaireByKey(Integer key) {
		Secretaire secretaire = null;
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection()
					.prepareStatement("select * from compte where id=? and compte_type='S'");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				secretaire = getSecretaire(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		return secretaire;
	}

	@Override
	public Medecin findMedecinByKey(Integer key) {
		Medecin medecin = null;
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection()
					.prepareStatement("select * from compte where id=? and compte_type='M'");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				medecin = getMedecin(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		return medecin;
	}
}
