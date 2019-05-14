package com.example.gomovie;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseField;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class Ticket {
        // 利用模板生成pdf
        public void pdfout(Map<String,Object> map) {
            // 模板路径
            String templatePath = "C:\\Users\\Administrator\\Nox_share\\OtherShare\\payed_ticket.pdf";
            // 生成的新文件路径
            String newPDFPath = "C:/Users/Administrator/Nox_share/OtherShare/payed.pdf";
            PdfReader reader;
            FileOutputStream out;
            ByteArrayOutputStream bos;
            PdfStamper stamper;
            try {
                //给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
                // 输出流
                out = new FileOutputStream(newPDFPath);
                // 读取pdf模板
                reader = new PdfReader(templatePath);
                bos = new ByteArrayOutputStream();
                stamper = new PdfStamper(reader, bos);
                AcroFields form = stamper.getAcroFields();
                Map<String,Object> qrcodeFields=(Map<String, Object>) map.get("qrcodeFields");
                //遍历二维码字段
                for (Map.Entry<String, Object> entry : qrcodeFields.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    // 获取属性的类型
                    if(value != null && form.getField(key) != null){
                        //获取位置(左上右下)
                        FieldPosition fieldPosition = form.getFieldPositions(key).get(0);
                        //绘制二维码
                        float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
                        BarcodeQRCode pdf417 = new BarcodeQRCode(value.toString(), (int)width, (int)width, null);
                        //生成二维码图像
                        Image image128 = pdf417.getImage();
                        //绘制在第一页
                        PdfContentByte cb = stamper.getOverContent(1);
                        //左边距(居中处理)
                        float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
                        //条码位置
                        image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
                        //加入条码
                        cb.addImage(image128);
                    }
                }

                //遍历条码字段
                Map<String,Object> barcodeFields=(Map<String, Object>) map.get("barcodeFields");
                for (Map.Entry<String, Object> entry : barcodeFields.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    // 获取属性的类型
                    if(value != null && form.getField(key) != null){
                        //获取位置(左上右下)
                        FieldPosition fieldPosition = form.getFieldPositions(key).get(0);
                        //绘制条码
                        Barcode128 barcode128 = new Barcode128();
                        //字号
                        barcode128.setSize(10);
                        //条码高度
                        barcode128.setBarHeight(45);
                        //条码与数字间距
                        barcode128.setBaseline(10);
                        //条码值
                        barcode128.setCode(value.toString());
                        barcode128.setStartStopText(false);
                        barcode128.setExtended(true);
                        //绘制在第一页
                        PdfContentByte cb = stamper.getOverContent(1);
                        //生成条码图片
                        Image image128 = barcode128.createImageWithBarcode(cb, null, null);
                        //左边距(居中处理)
                        float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
                        //条码位置
                        image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
                        //加入条码
                        cb.addImage(image128);
                    }
                }
                java.util.Iterator<String> it = form.getFields().keySet()
                        .iterator();
                while (it.hasNext())
                {
                    String name = it.next().toString();
                    System.out.println(name);
                }
                //movie name
                form.setFieldProperty("movie", "textfont", bf, null);//设置字体
                form.setField("movie", "MOVIENAME");
                //ticket time
                form.setFieldProperty("time", "textfont", bf, null);//设置字体
                form.setField("time", "19/04/17 11:30");
                //ticket room name
                form.setFieldProperty("room", "textfont", bf, null);
                form.setField("room", "1");
                //ticket row and place
                form.setFieldProperty("row", "textfont", bf, null);
                form.setField("row","3");
                form.setFieldProperty("place", "textfont", bf, null);
                form.setField("place","8");
                // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
                stamper.setFormFlattening(true);
                stamper.close();
                Document doc = new Document();
                //Font font = new Font(bf, 40);
                PdfCopy copy = new PdfCopy(doc, out);
                doc.open();
                PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
                copy.addPage(importPage);
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        public void main() {
            //条形码map
            Map<String,Object> barcodeFields = new HashMap<String, Object>();
            barcodeFields.put("bar","12345678910");
            //二维码map
            Map<String,Object> qrcodeFields = new HashMap<String, Object>();
            qrcodeFields.put("qr","https://blog.csdn.net/qq_26173219");
            //组装map传过去
            Map<String,Object> o=new HashMap<String, Object>();
            o.put("barcodeFields",barcodeFields);
            o.put("qrcodeFields",qrcodeFields);
            //执行
            pdfout(o);
            System.out.println("print out a PDF!");
        }
}


