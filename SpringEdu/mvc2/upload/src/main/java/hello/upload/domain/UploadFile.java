package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    private String uploadFileName; // 사용자가 설정한 파일 이름
    private String storeFileName; // 실제 서버에 저장되는 파일 이름 (중복 X)

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
