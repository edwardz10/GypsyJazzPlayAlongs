package com.websystique.springmvc.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Track;

@Service("trackService")
public class TrackService {
	private static Log LOG = LogFactory.getLog(TrackService.class);	
	
	private static String XLS_FILENAME = "/Gypsy Jazz Standards.xlsx";
	private List<Track> tracks = new ArrayList<Track>();
	
	public TrackService() {
		System.out.println("TrackService: get here");
		InputStream is = null;

		try {
			is = getClass().getResourceAsStream(XLS_FILENAME);
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Row row = null;
			System.out.println("TrackService: " + sheet.getLastRowNum() + " rows in " + XLS_FILENAME);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);

				String trackName = getCellText(row.getCell(0));
				String trackId = getCellText(row.getCell(1));
				
				if (!trackName.isEmpty() && !trackId.isEmpty()) {
					tracks.add(new Track(trackName, trackId));
				}
			}
		} catch(Exception e) {
			LOG.warn("Faled to load tracks: " + e , e);
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				LOG.error("Failed to close '" + XLS_FILENAME + "': " + e, e);
			}
		}

		System.out.println("TrackService: tracks: " + tracks);
		LOG.info("Tracks: " + tracks);
	}
	
	public List<Track> getAllTracks() {
		return tracks;
	}

	public static String getCellText(Cell cell) {
		switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            return cell.getRichStringCellValue().getString();
        case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue().toString();
            } else {
            	return String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
            }
        case Cell.CELL_TYPE_BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        case Cell.CELL_TYPE_FORMULA:
            return cell.getCellFormula();
        default:
            return "";
		}
	}	

}
