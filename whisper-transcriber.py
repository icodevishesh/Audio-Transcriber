import os
os.environ["PATH"] += os.pathsep + r"C:\Program Files\ffmpeg-7.1.1-essentials_build\ffmpeg-7.1.1-essentials_build\bin"
import whisper
import sys
import warnings

# Suppress specific warning
warnings.filterwarnings("ignore", message="FP16 is not supported on CPU*")

if len(sys.argv) < 2:
    print("Usage: python whisper-transcriber.py <audio_file_path>")
    sys.exit(1)

audio_path = sys.argv[1]
model = whisper.load_model("base")
result = model.transcribe(audio_path)
print(result["text"])
