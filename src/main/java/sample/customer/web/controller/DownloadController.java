package sample.customer.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DownloadController {

    static final String dir = "/tmp/";
    static final String[] targetFiles = {"hoge1.csv", "hoge2.csv", "hoge3.csv"};
    static String fileName;
    static String zipFileName;

    static {
        try {
            fileName = new String("サンプル.csv".getBytes("MS932"), "ISO-8859-1");
            zipFileName = new String("kinmu.zip".getBytes("MS932"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
        }
    }

    @RequestMapping
    public void download(HttpServletResponse res) throws Exception {

        res.setContentType("text/csv;charset=MS932");
        res.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        OutputStream os = res.getOutputStream();

        InputStream in = new FileInputStream(dir + targetFiles[0]);

        byte[] b = new byte[1024];
        int len;
        while((len = in.read(b)) != -1) {
            os.write(b, 0, len);
        }
        in.close();
        os.close();
    }

    @RequestMapping("zip")
    public void downloadZip(HttpServletResponse res) throws Exception {

        res.setContentType("application/octet-stream;charset=MS932");
        res.setHeader("Content-Disposition", "attachment; filename=" + zipFileName);
        res.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream os = res.getOutputStream();
        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(os));
        ZipEntry ze = new ZipEntry(targetFiles[1]);
        zos.putNextEntry(ze);

        InputStream in = new BufferedInputStream(new FileInputStream(dir + targetFiles[1]));

        byte[] b = new byte[1024];
        int len;
        while((len = in.read(b)) != -1) {
            zos.write(b, 0, len);
        }
        in.close();
        zos.closeEntry();
        zos.close();
    }

    @RequestMapping("zip/all")
    public void downloadZipAll(HttpServletResponse res) throws Exception {

        res.setContentType("application/octet-stream;charset=MS932");
        res.setHeader("Content-Disposition", "attachment; filename=" + zipFileName);
        res.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream os = res.getOutputStream();
        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(os));

        for(int i=0; i<targetFiles.length; i++){

            ZipEntry ze = new ZipEntry(targetFiles[i]);
            zos.putNextEntry(ze);

            InputStream in = new BufferedInputStream(new FileInputStream(dir + targetFiles[i]));

            byte[] b = new byte[1024];
            int len;
            while((len = in.read(b)) != -1) {
                zos.write(b, 0, len);
            }
            in.close();
            zos.closeEntry();
        }
        zos.close();
    }
}