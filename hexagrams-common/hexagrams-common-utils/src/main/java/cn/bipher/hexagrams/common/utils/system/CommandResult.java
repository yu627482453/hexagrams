package cn.bipher.hexagrams.common.utils.system;

import cn.bipher.hexagrams.common.utils.util.StreamUtil;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class CommandResult {

	protected File stdOut;

	protected File stdErr;

	private Charset charset;

	@Getter
	protected LocalDateTime startTime;

	@Getter
	protected LocalDateTime endTime;

	protected String strOutput = null;

	protected String strError = null;

	public static CommandResult of(File stdOut, File stdErr, LocalDateTime startTime, LocalDateTime endTime,
			Charset charset) {
		CommandResult result = new CommandResult();
		result.stdOut = stdOut;
		result.stdErr = stdErr;
		result.charset = charset;
		result.startTime = startTime;
		result.endTime = endTime;
		return result;
	}

	public File stdOut() {
		return stdOut;
	}

	public File stdErr() {
		return stdErr;
	}

	public String stdOutStr() throws IOException {
		if (!StringUtils.hasText(strOutput)) {
			try (FileInputStream output = new FileInputStream(stdOut)) {
				strOutput = StreamUtil.toString(output, StreamUtil.DEFAULT_SIZE, charset);
			}
		}
		return strOutput;
	}

	public String stdErrStr() throws IOException {
		if (!StringUtils.hasText(strError)) {
			try (FileInputStream error = new FileInputStream(stdErr)) {
				strError = StreamUtil.toString(error, StreamUtil.DEFAULT_SIZE, charset);
			}
		}
		return strError;
	}

	public InputStream stdOutStream() throws IOException {
		return Files.newInputStream(stdOut.toPath());
	}

	public InputStream stdErrStream() throws IOException {
		return Files.newInputStream(stdErr.toPath());
	}

	public void clean() {
		try {
			Files.delete(stdOut.toPath());
		}
		catch (Exception e) {
			//
		}
		try {
			Files.delete(stdErr.toPath());
		}
		catch (Exception e) {
			//
		}
	}

}
