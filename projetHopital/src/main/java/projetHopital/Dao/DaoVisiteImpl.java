package projetHopital.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import projetHopital.model.Bureau;
import projetHopital.model.Visite;


public class DaoVisiteImpl implements DaoVisite {
	DaoVisiteImpl() {
	}

	@Override
	public void insert(Visite obj) {
		PreparedStatement ps = null;
		Connection connection = null;
		try {
			connection = Context.getContext().getConnection();
			ps = connection.prepareStatement(
					"insert into visite(patient_id,medecin_id,visite_cout,visite_salle,visite_date) values(?,?,20,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, obj.getPatient_id());
			ps.setInt(2, obj.getMedecin_id());
			ps.setString(3, obj.getSalle().toString()); 
			ps.setDate(4, (Date) (obj.getDate()));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setVisite_id(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}

	}

	@Override
	public void update(Visite obj) {
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement(
					"update visite set patient_id=?,medecin_id=?,visite_cout=20,visite_salle=?,visite_date=?, where visite_id=?");
			ps.setInt(1, obj.getPatient_id());
			ps.setInt(2, obj.getMedecin_id());
			ps.setString(3, obj.getSalle().toString()); 
			ps.setDate(4, (Date) obj.getDate());			
			ps.setInt(5, obj.getVisite_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}

	}

	@Override
	public void delete(Visite obj) {
		deleteByKey(obj.getVisite_id());
	}

	@Override
	public void deleteByKey(Integer key) {
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement("delete from visite where visite_id=?");
			ps.setInt(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
	}

	private Visite getVisite(ResultSet rs) throws SQLException {
		// @formatter:off
		Visite Visite=new Visite(
								rs.getInt("visite_id"), 
								rs.getInt("patient_id"),
								rs.getInt("medecin_id"),
								Bureau.valueOf(rs.getString("visite_salle")),
								rs.getDate("visite_date")
								);	
		return Visite;
	}

	public Visite findByKey(Integer key) {
		Visite visite = null;
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement("select * from visite where visite_id=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				visite = getVisite(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		return visite;
	}
	
	public List<Visite> findMedecinByKey(Integer key) {
		Visite visite = null;
		List<Visite> visites = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement("select * from visite where medecin_id=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				visite = getVisite(rs);
				visites.add(visite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		return visites;
	}
	
	public List<Visite> findPatientByKey(Integer key) {
		Visite visite = null;
		List<Visite> visites = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement("select * from visite where patient_id=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				visite = getVisite(rs);
				visites.add(visite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		return visites;
	}

	@Override
	public List<Visite> findAll() {
		List<Visite> visites = new ArrayList<>();
		Visite visite = null;
		Statement st = null;
		try {
			st = Context.getContext().getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from visite ");
			while (rs.next()) {			
				visite = getVisite(rs);
				visites.add(visite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(st);
			Context.destroy();
		}
		return visites;
	}

}
