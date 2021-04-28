package com.ziad.professeurespace.notes;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;

public interface NoteInterface {
	public void readExcel(MultipartFile file, String type)
			throws DataNotFoundExceptions, EntityNotFoundException, IOException, CSVReaderOException;
}
