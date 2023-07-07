/*
	ASSIGNMENT	: TRANG WEB QUẢN LÝ BÁN HÀNG
	Nhóm	: 4
*/




-- TẠO DATABASE
CREATE DATABASE JAVA4_ASM_NHOM4
GO
USE JAVA4_ASM_NHOM4
GO





-- TẠO BẢNG
CREATE TABLE USERS(
	Username VARCHAR(30) primary key,
	[Password] VARCHAR(30) NULL,
	FullName NVARCHAR(50) NOT NULL,
	Gender BIT NOT NULL,
	Phone VARCHAR(10) NOT NULL,
	Email VARCHAR(50) NOT NULL,
	Adress NVARCHAR(255) NOT NULL,
	[Role] bit NOT NULL,
)

CREATE TABLE CATEGORY(
	ID_Category VARCHAR(5) primary key,
	[Ca_Name] NVARCHAR(50) NOT NULL
)

CREATE TABLE PRODUCTS(
	ID_Product INT IDENTITY primary key,
	[Pr_Name] NVARCHAR(255) NOT NULL,
	Price INT NOT NULL,
	ID_Category VARCHAR(5) NOT NULL,
	[Image] VARCHAR(255) NOT NULL,
	Info  NVARCHAR(max) NOT NULL,

	constraint FK_PRODUCT_CATEGORY foreign key (ID_Category) references CATEGORY(ID_Category)
)


CREATE TABLE BILL (
	ID_Bill INT IDENTITY primary key,
	Username VARCHAR(30) NULL,
	[Date] DATE NOT NULL,
	[Status] BIT NOT NULL,
	Total INT NOT NULL

	constraint FK_BILL_USER foreign key (Username) references USERS(Username),
)

CREATE TABLE BILL_DETAIL (
	ID_DETAIL INT IDENTITY primary key,
	ID_Product INT NOT NULL,
	ID_Bill INT NOT NULL,
	Amount INT NOT NULL,

	constraint FK_BILL_DETAIL_PRODUCT foreign key (ID_Product) references PRODUCTS(ID_Product),
	constraint FK_BILL_DETAIL_BILL foreign key (ID_Bill) references BILL(ID_Bill)

	ON DELETE CASCADE
)

-- THÊM DỮ LIỆU
INSERT INTO USERS VALUES
	('trungdb', '123456', N'Phạm Bá Trung', 1, '0975475899', 'trungpb@gmail.com', N'16 Thích Quảng Đức, Phú Nhuận', 1),
	('phupd', '123456', N'Phương Đình Phú', 1, '0985748574', 'phupd@gmail.com', N'100 Đông Bắc, Quận 12', 0),
	('thaopv', '123456', N'Phan Văn Thảo', 1, '0875847584', 'thaopv@gmail.com', N'99 Tô Ký, Quận 12', 0),
	('manhnd', '123456', N'Nguyễn Đình Mạnh', 1, '0876857485', 'manhnd@gmail.com', N'33 Nguyễn Trọng Tuyển, Phú Nhuận', 0)


INSERT INTO CATEGORY VALUES
	('LMH01', N'Phone'),
	('LMH02', N'Laptop'),
	('LMH03', N'Tablet'),
	('LMH04', N'Accessory'),
	('LMH05', N'Smart Watch')


INSERT INTO PRODUCTS VALUES
	(N'Samsung Galaxy S22+ 5G', 25990000, 'LMH01', 'Galaxy-S22-Plus-White-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy S22 Ultra 5G 128GB', 30990000, 'LMH01', 'Galaxy-S22-Ultra-Burgundy-600x600.jpg', N'dfđh'),
	(N'IPhone 12 Pro Max 512GB', 30990000, 'LMH01', 'iphone-12-pro-max-xam-new-600x600-600x600.jpg', N'dfđh'),
	(N'iPhone 12 Pro 512GB', 28490000, 'LMH01', 'iphone-12-pro-xam-new-600x600-600x600.jpg', N'dfđh'),
	(N'iPhone 13 Pro Max', 33990000, 'LMH01', 'iphone-13-pro-max-gold-1-600x600.jpg', N'dfđh'),
	(N'iPhone 13 Pro 128GB', 30990000, 'LMH01', 'iphone-13-pro-sierra-blue-600x600.jpg', N'dfđh'),
	(N'iPhone 11', 14990000, 'LMH01', 'iphone-xi-xanhla-600x600.jpg', N'dfđh'),
	(N'OPPO Find X5 Pro 5G', 32990000, 'LMH01', 'oppo-find-x5-pro-den-thumb-600x600.jpg', N'dfđh'),
	(N'OPPO Reno7 series', 10490000, 'LMH01', 'oppo-reno7-z-5g-thumb-1-1-600x600.jpg', N'dfđh'),
	(N'Realme C3', 4290000, 'LMH01', 'realme-c35-thumb-new-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy A03', 2990000, 'LMH01', 'samsung-galaxy-a03-xanh-thumb-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy A52s 5G 128GB', 10990000, 'LMH01', 'samsung-galaxy-a52s-5g-mint-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy S21 Ultra 5G 128GB', 30990000, 'LMH01', 'samsung-galaxy-s21-ultra-bac-600x600-1-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy Z Flip3 5G 128GB Flex Your Way', 24990000, 'LMH01', 'Samsung-Galaxy-Z-Flip-3-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy Z Fold3 5G', 41990000, 'LMH01', 'samsung-galaxy-z-fold-3-silver-1-600x600.jpg', N'dfđh'),
	(N'Vivo Y15 serie', 3490000, 'LMH01', 'Vivo-y15s-2021-xanh-den-660x600.jpg', N'dfđh'),
	(N'Xiaomi 11', 10990000, 'LMH01', 'Xiaomi-11T-White-1-2-3-600x600.jpg', N'dfđh'),
	(N'Xiaomi Redmi Note 11 Series', 7490000, 'LMH01', 'xiaomi-redmi-note-11-pro-trang-thumb-600x600.jpg', N'dfđh'),
	(N'Xiaomi Redmi Note 11S series', 6490000, 'LMH01', 'xiaomi-redmi-note-11s-5g- thumb-600x600.jpg', N'dfđh'),
	(N'Xiaomi Redmi Note 10 Pro (8GB/128GB)', 7490000, 'LMH01', 'xiaomi-redmi-note-10-pro-thumb-xam-600x600-600x600.jpg', N'dfđh'),

	(N'Acer Nitro 5 Gaming AN515 45 R6EV R5 5600H (NH.QBMSV.006)', 1000, 'LMH02', 'acer-nitro-5-gaming-an515-45-r6ev-r5-5600h-8gb-600x600.jpg', N'dfđh'),
	(N'Acer Nitro 5 Gaming AN515 57 5831 i5 11400H (NH.QDGSV.003)', 1000, 'LMH02', 'acer-nitro-5-gaming-an515-57-5831-i5-nhqdgsv003-600x600.jpg', N'dfđh'),
	(N'Acer TravelMate B3 TMB311 31 P49D N5030 (NX.VNFSV.005)', 1000, 'LMH02', 'acer-travelmate-b3-tmb311-31-p49d-n5030-nxvnfsv005-081221-100723-600x600.jpg', N'dfđh'),
	(N'Asus Gaming ROG Flow Z13 GZ301Z i7 12700H (LD110W)', 1000, 'LMH02', 'asus-gaming-rog-flow-z13-gz301z-i7-ld110w-160322-120057-600x600.jpg', N'dfđh'),
	(N'Lenovo Gaming Legion 5 15ITH6 i7 11800H (82JK00FNVN)', 1000, 'LMH02', 'lenovo-gaming-legion-5-15ith6-i7-82jk00fnvn-180322-100552-600x600.jpg', N'dfđh'),
	(N'Lenovo Yoga 9 14ITL5 i7/1185G7 (82BG006EVN)', 1000, 'LMH02', 'lenovo-yoga-9-14itl5-i7-82bg006evn-21-600x600.jpg', N'dfđh'),
	(N'MacBook Air M1 2020 7-core GPU', 1000, 'LMH02', 'macbook-air-m1-2020-gray-600x600.jpg', N'dfđh'),
	(N'MacBook Pro M1 2020', 1000, 'LMH02', 'macbook-pro-m1-2020-gray-600x600.jpg', N'dfđh'),
	(N'MSI Gaming GE66 Raider 11UG i7 11800H (258VN)', 1000, 'LMH02', 'msi-gaming-ge66-raider-11ug-i7-258vn-600x600.jpg', N'dfđh'),
	(N'MSI Gaming GS66 Stealth 11UG i7 11800H (219VN)', 1000, 'LMH02', 'msi-gaming-gs66-stealth-11ug-i7-219vn-600x600.jpg', N'dfđh'),

	(N'Alcatel 3T8', 2990000, 'LMH03', 'Alcatel-3T8-1-600x600.jpg', N'dfđh'),
	(N'iPad Air 5 M1 Wifi Cellular 64GB', 20990000, 'LMH03', 'ipad-air-5-wifi-cellular-tim-thumb-600x600.jpg', N'dfđh'),
	(N'iPad Pro M1 11 inch 5G', 29990000, 'LMH03', 'ipad-pro-2021-11-inch-silver-600x600.jpg', N'dfđh'),
	(N'iPad Pro M1 12.9 inch WiFi', 30990000, 'LMH03', 'ipad-pro-2021-129-inch-gray-600x600.jpg', N'dfđh'),
	(N'iPad Pro M1 11 inch WiFi', 19990000, 'LMH03', 'ipad-pro-m1-11-inch-wifi-gray-9-600x600.jpg', N'dfđh'),
	(N'iPad Pro M1 12.9 inch 5G', 29290000, 'LMH03', 'ipad-pro-m1-129-inch-wifi-gray-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy Tab A7 Lite', 4490000, 'LMH03', 'samsung-galaxy-tab-a7-lite-gray-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy Tab S8', 20990000, 'LMH03', 'Samsung-Galaxy-tab-s8-black-thumb-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy Tab S8 Ultra', 30990000, 'LMH03', 'samsung-galaxy-tab-s8-ultra-1-600x600.jpg', N'dfđh'),
	(N'Samsung Galaxy Tab S8+', 25990000, 'LMH03', 'Tab-S8+-Black-1-600x600.jpg', N'dfđh'),

	(N'AVA PJ JP207', 450000, 'LMH04', '229277-600x600.jpg', N'dfđh'),
	(N'AVA LA Y65', 400000, 'LMH04', '234502-600x600.jpg', N'dfđh'),
	(N'AVA+ LA Y68', 350000, 'LMH04', 'ava-plus-y68-1-600x600.jpg', N'dfđh'),
	(N'AVA+ JP208', 500000, 'LMH04', 'pin-polymer-10000mah-ava-jp208-ava-600x600.jpg', N'dfđh'),
	(N'Xmobile PowerLite P181P', 850000, 'LMH04', 'pin-polymer-10000mah-khong-day-magnetic-type-c-qc30-xmobile-powerlite-p181p-thumb2-600x600.jpg', N'dfđh'),
	(N'Anker PowerCore Essential A1287', 1400000, 'LMH04', 'pin-polymer-20000mah-type-c-pd-20w-anker-a1287-avatar-600x600.jpg', N'dfđh'),
	(N'AVA LJ JP195', 300000, 'LMH04', 'pin-sac-du-phong-7500mah-ava-lj-jp195-avatar-1-600x600.jpg', N'dfđh'),
	(N'Xiaomi Power Bank 3 Ultra Compact', 790000, 'LMH04', 'polymer-10000mah-xiaomi-ultra-compact-avt-1-1-600x600.jpg', N'dfđh'),
	(N'AVA PJ JP191S', 450000, 'LMH04', 'sac-du-phong-polymer-10000mah-ava-pj-jp191s-avatar-1-600x600.jpg', N'dfđh'),
	(N'Xmobile PowerBox P72D', 900000, 'LMH04', 'sac-du-phong-polymer-15000mah-type-c-xmobile-p72d-avatar-1-600x600.jpg', N'dfđh'),

	(N'Apple Watch S5 LTE 40mm viền thép dây thép', 17990000, 'LMH05', 'apple-watch-s5-lte-44mm-day-thep-bac-231023-fix-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'Apple Watch S6 40mm viền nhôm dây silicone', 9990000, 'LMH05', 'apple-watch-s6-40mm-vien-nhom-day-cao-su-do-thumb-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'BeU B2', 590000, 'LMH05', 'beu-b2-hong-thumb-1-1-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'Garmin Fenix 6 Sapphire dây silicone viền Titanium', 22490000, 'LMH05', 'garmin-fenix-6-day-silicone-thumb-1-1-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'Apple Watch S5 LTE 40mm viền thép dây thép', 17990000, 'LMH05', 's5-lte-40mm-vien-thep-day-thep-bac-thumb-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'Apple Watch S6 LTE 44mm viền nhôm dây silicone', 12990000, 'LMH05', 's6-lte-44mm-vien-nhom-day-cao-su-xanh-thumb-1-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'Samsung Galaxy Watch 4 40mm Vàng Hồng', 6490000, 'LMH05', 'samsung-galaxy-watch-4-40mm-vang-hong-thumb-1-2-3-4-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'Samsung Galaxy Watch 4 44mm Đen', 6990000, 'LMH05', 'samsung-galaxy-watch-4-44mm-thumb-02-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'Samsung Galaxy Watch 4 Classic 42mm Trắng', 8490000, 'LMH05', 'samsung-galaxy-watch-4-classic-42mm-trang-thumb-1-2-3-600x600.jpg', N'mạnh bảo nhập abc gì đó'),
	(N'Forerunner 245 Music', 8999000, 'LMH05', 'garmin-forerunner-245-music-thumb-1-1-600x600.jpg', N'mạnh bảo nhập abc gì đó')

