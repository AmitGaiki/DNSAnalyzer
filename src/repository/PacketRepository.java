/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.DBConnection;
import entity.PacketEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amit
 */
public class PacketRepository {

	private Connection connection;
	private DBConnection dBConnection;

	public PacketRepository(Connection connection) {
		this.connection = connection;
	}

	public DBConnection getdBConnection() {
		return dBConnection;
	}

	public void setdBConnection(DBConnection dBConnection) {
		this.dBConnection = dBConnection;
	}
	
	

	public void save(PacketEntity packetEntity) {
		
		try {
			dBConnection.populateTable("Packets",packetEntity.getTimeStamp(),packetEntity.getTransactionId(),packetEntity.getPacketType(), packetEntity.getQueryType(), packetEntity.isAuthResponse(), packetEntity.isRecursionDesired(), packetEntity.isRecursionAvailable(), packetEntity.getResponseCode(), packetEntity.getQuestionCount(), packetEntity.getResponseCount(), packetEntity.getAuthCount(), packetEntity.getAdditionalCount(), packetEntity.getQuestionName(), packetEntity.getQuestionType(), packetEntity.getQuestionClass(), packetEntity.getPayload());
		} catch (SQLException ex) {
			Logger.getLogger(PacketRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public boolean findByTransactionIdAndPacketType(String transactionId, String packetType) {

		ResultSet rs = null;
		try {
			rs = connection.prepareStatement("SELECT TRANSACTION_ID FROM " + dBConnection.getDBName() + ".Packets " + "WHERE TRANSACTION_ID=" + transactionId + " AND TYPE_OF_PACKET=\'Query\'").executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(PacketRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			return rs.next();
		} catch (SQLException ex) {
			Logger.getLogger(PacketRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
}
