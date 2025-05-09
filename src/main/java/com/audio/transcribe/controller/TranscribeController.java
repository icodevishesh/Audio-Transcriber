package com.audio.transcribe.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/transcribe")
public class TranscribeController {

    @PostMapping
    public ResponseEntity<String> transcribeAudio(@RequestParam("file") MultipartFile file)
            throws IOException, InterruptedException {

        File tempFile = File.createTempFile("audio", ".wav", new File(System.getProperty("java.io.tmpdir")));
        file.transferTo(tempFile);


        ProcessBuilder processBuilder = new ProcessBuilder(
            "python",
            "C:\\Users\\Vishesh Purkait\\Desktop\\wisperaipy\\whisper-transcriber.py",
            tempFile.getAbsolutePath()
        );

        Map<String, String> env = processBuilder.environment();
        System.out.println("Environment Variables:");
        env.forEach((key, value) -> System.out.println(key + ": " + value));


        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        // Wait for process to complete
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Whisper transcription failed with code: " + exitCode + "\nOutput:\n" + output);
        }

        if (!tempFile.delete()) {
            System.err.println("Warning: Could not delete temp file: " + tempFile.getAbsolutePath());
        }

        return ResponseEntity.ok(output.toString().trim());
    }
}

	
	
	
	
//	private final OpenAiAudioTranscriptionModel transcriptionModel;
//	
//
//	
//	   public TranscribeController(OpenAiAudioTranscriptionModel transcriptionModel) {
//	        this.transcriptionModel = transcriptionModel;
//	    }
//    
//	   @PostMapping
//	public ResponseEntity<String> transcribeAudio(@RequestParam("file") MultipartFile file) 
//			throws IOException{
//			File tempFile = File.createTempFile("audio", ".wav");
//			file.transferTo(tempFile);
//			
//			OpenAiAudioTranscriptionOptions transcriptionOptions = OpenAiAudioTranscriptionOptions
//					.builder()
//				    .responseFormat(TranscriptResponseFormat.TEXT)
//				    .language("en")
//				    .temperature(0f)
//				    .build();
//			
//			 FileSystemResource audioFile = new FileSystemResource(tempFile);
//
//		        AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(
//		        		audioFile, 
//		        		transcriptionOptions);
//		        AudioTranscriptionResponse response = this.transcriptionModel.call(
//		        		transcriptionRequest);
//		        
//		        tempFile.delete();
//		        return new ResponseEntity<>(response.getResult().getOutput(),HttpStatus.OK);
//	}
		
