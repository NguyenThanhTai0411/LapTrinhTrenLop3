package stt33_23715171_NguyenThanhTai;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class HangThucPham {
    private final String maHang;
    private String tenHang;
    private double donGia;
    private Date ngaySanXuat;
    private Date ngayHetHan;

    // Constructor đầy đủ tham số
    public HangThucPham(String maHang, String tenHang, double donGia, Date ngaySanXuat, Date ngayHetHan) {
        if (maHang == null || maHang.isEmpty()) {
            throw new IllegalArgumentException("Mã hàng không được để rỗng");
        }
        this.maHang = maHang;
        this.tenHang = (tenHang == null || tenHang.isEmpty()) ? "xxx" : tenHang;
        this.donGia = (donGia >= 0) ? donGia : 0;
        setNgaySanXuat(ngaySanXuat);
        setNgayHetHan(ngayHetHan);
    }

    // Constructor chỉ có mã hàng
    public HangThucPham(String maHang) {
        this(maHang, "xxx", 0, new Date(), new Date());
    }

    // Getters và setters
    public String getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        if (tenHang == null || tenHang.isEmpty()) {
            this.tenHang = "xxx";
        } else {
            this.tenHang = tenHang;
        }
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia >= 0) {
            this.donGia = donGia;
        } else {
            this.donGia = 0;
        }
    }

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        if (ngaySanXuat != null && ngaySanXuat.before(new Date())) {
            this.ngaySanXuat = ngaySanXuat;
        } else {
            this.ngaySanXuat = new Date();
        }
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        if (ngayHetHan != null && ngayHetHan.after(this.ngaySanXuat)) {
            this.ngayHetHan = ngayHetHan;
        } else {
            this.ngayHetHan = this.ngaySanXuat;
        }
    }

    // Kiểm tra hàng thực phẩm đã hết hạn chưa
    public boolean daHetHan() {
        return ngayHetHan.before(new Date());
    }

    // Phương thức toString
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        
        String result = "Mã hàng: " + maHang + "\t\t" +
                        "Tên hàng: " + tenHang + "\t\t" +
                        "Đơn giá: " + decimalFormat.format(donGia) + "\t\t" +
                        "Ngày sản xuất: " + dateFormat.format(ngaySanXuat) + "\t\t" +
                        "Ngày hết hạn: " + dateFormat.format(ngayHetHan) + "\t\t" +
                        "Trạng thái: " + (daHetHan() ? "Hàng đã hết hạn" : "Hàng còn hạn sử dụng");
        return result;
    }

    // Lớp chứa hàm main để kiểm thử
    public static void main(String[] args) {
        // Tạo 3 đối tượng
        Calendar cal = Calendar.getInstance();
        
        // Tạo đối tượng thứ nhất
        cal.set(2024, Calendar.SEPTEMBER, 1);
        Date ngaySX1 = cal.getTime();
        cal.set(2025, Calendar.SEPTEMBER, 1);
        Date ngayHH1 = cal.getTime();
        HangThucPham htp1 = new HangThucPham("MH01", "quan bo", 75000, ngaySX1, ngayHH1);

        // Tạo đối tượng thứ hai với ngày hết hạn trước ngày sản xuất
        cal.set(2023, Calendar.AUGUST, 20);
        Date ngaySX2 = cal.getTime();
        cal.set(2023, Calendar.AUGUST, 15); // ngày hết hạn trước ngày sản xuất
        Date ngayHH2 = cal.getTime();
        HangThucPham htp2 = new HangThucPham("MH02", "Thịt gà", 75000, ngaySX2, ngayHH2);

        // Tạo đối tượng thứ ba
        cal.set(2023, Calendar.JULY, 1);
        Date ngaySX3 = cal.getTime();
        cal.set(2024, Calendar.JULY, 1);
        Date ngayHH3 = cal.getTime();
        HangThucPham htp3 = new HangThucPham("MH03", "Bánh mì", 20000, ngaySX3, ngayHH3);

        // Xuất thông tin các mặt hàng
        System.out.println(htp1);
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(htp2);
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(htp3);
    }
}