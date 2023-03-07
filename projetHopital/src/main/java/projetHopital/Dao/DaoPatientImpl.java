package projetHopital.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import projetHopital.model.Patient;

public class DaoPatientImpl implements DaoPatient{
	
	private Patient getPatient(ResultSet rs) throws SQLException{
		return new Patient(rs.getInt("patient_id"), rs.getString("patient_nom"), rs.getString("patient_prenom"));
	}

	@Override
	public void insert(Patient obj) {
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement(
					"insert into patient(patient_nom,patient_preneom) values(?,?) ",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setPatient_id(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		
	}

	@Override
	public void update(Patient obj) {
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement("update patient set patient_nom=?,patient_prenom=? where patient_id=?");
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setInt(3, obj.getPatient_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
	}

	@Override
	public void delete(Patient obj) {
		deleteByKey(obj.getPatient_id());
	}

	@Override
	public void deleteByKey(Integer key) {
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection()
					.prepareStatement("delete from patient where patient_id=?");
			ps.setInt(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		
	}

	@Override
	public Patient findByKey(Integer key) {
		Patient patient=null;
		PreparedStatement ps = null;
		try {
			ps = Context.getContext().getConnection().prepareStatement("select * from patient where patient_id=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
					patient = getPatient(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(ps);
			Context.destroy();
		}
		return patient;
	}

	@Override
	public List<Patient> findAll() {
		List<Patient> patients = new ArrayList<>();
		Patient patient = null;
		Statement st = null;
		try {
			st = Context.getContext().getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from patient");
			while (rs.next()) {
				patient = getPatient(rs);
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Closer.close(st);
			Context.destroy();
		}
		return patients;
	}

}
