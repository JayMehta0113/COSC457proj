-- Creating Roles_Permission table
CREATE TABLE IF NOT EXISTS Roles_Permission (
    role_Id INT PRIMARY KEY,
    role_Name VARCHAR(50),
    permission VARCHAR(255),
    description TEXT
);

-- Creating Guardian table
CREATE TABLE IF NOT EXISTS Guardian (
    guardian_Id INT PRIMARY KEY,
    first_Name VARCHAR(50),
    last_Name VARCHAR(50),
    relationship_To_Student VARCHAR(50),
    phone_Number VARCHAR(15),
    email VARCHAR(100),
    address VARCHAR(100),
    role_Id INT,
    FOREIGN KEY (role_Id) REFERENCES Roles_Permission(role_Id)
);

-- Creating Teacher table
CREATE TABLE IF NOT EXISTS Teacher (
    teacher_Id INT PRIMARY KEY,
    first_Name VARCHAR(50),
    middle_Name VARCHAR(50),
    last_Name VARCHAR(50),
    date_Of_Birth DATE,
    phone_Number VARCHAR(15),
    department VARCHAR(100),
    email VARCHAR(100),
    role_Id INT,
    FOREIGN KEY (role_Id) REFERENCES Roles_Permission(role_Id)
);

-- Creating Student table
CREATE TABLE IF NOT EXISTS Student (
    student_Id INT PRIMARY KEY,
    first_Name VARCHAR(50),
    middle_Name VARCHAR(50),
    last_Name VARCHAR(50),
    date_Of_Birth DATE,
    gender ENUM('M', 'F', 'O'),
    address VARCHAR(100),
    phone_Number VARCHAR(15),
    email VARCHAR(100),
    enrollment_Date DATE,
    grade_Level INT,
    guardian_First_Name VARCHAR(50),
    guardian_Last_Name VARCHAR(50),
    guardian_Id INT,
    homeroom_Teacher_Id INT,
    role_Id INT,
    FOREIGN KEY (guardian_Id) REFERENCES Guardian(guardian_Id),
    FOREIGN KEY (homeroom_Teacher_Id) REFERENCES Teacher(teacher_Id),
    FOREIGN KEY (role_Id) REFERENCES Roles_Permission(role_Id)
);

-- Creating Staff table
CREATE TABLE IF NOT EXISTS Staff (
    staff_Id INT PRIMARY KEY,
    first_Name VARCHAR(50),
    last_Name VARCHAR(50),
    position VARCHAR(50),
    phone_Number VARCHAR(15),
    email VARCHAR(100),
    department VARCHAR(50),
    role_Id INT,
    FOREIGN KEY (role_Id) REFERENCES Roles_Permission(role_Id)
);

-- Creating Admin table
CREATE TABLE IF NOT EXISTS Admin (
    admin_Id INT PRIMARY KEY,
    first_Name VARCHAR(50),
    last_Name VARCHAR(50),
    position VARCHAR(50),
    email VARCHAR(100),
    phone_Number VARCHAR(15),
    office_Location VARCHAR(100),
    role_Id INT,
    FOREIGN KEY (role_Id) REFERENCES Roles_Permission(role_Id)
);

-- Creating Subject table
CREATE TABLE IF NOT EXISTS Subject (
    subject_Id INT PRIMARY KEY,
    subject_Name VARCHAR(100),
    description TEXT
);

-- Creating Course table
CREATE TABLE IF NOT EXISTS Course (
    course_Id INT PRIMARY KEY,
    course_Name VARCHAR(100),
    description TEXT,
    grade_Level INT,
    subject_Id INT,
    FOREIGN KEY (subject_Id) REFERENCES Subject(subject_Id)
);

-- Creating Teaches table
CREATE TABLE IF NOT EXISTS Teaches (
    teacher_Id INT,
    course_Id INT,
    PRIMARY KEY (teacher_Id, course_Id),
    FOREIGN KEY (teacher_Id) REFERENCES Teacher(teacher_Id),
    FOREIGN KEY (course_Id) REFERENCES Course(course_Id)
);

-- Creating Classroom table
CREATE TABLE IF NOT EXISTS Classroom (
    classroom_Id INT PRIMARY KEY,
    room_Number VARCHAR(3),
    capacity INT,
    class_Type VARCHAR(50)
);

-- Creating Schedule table
CREATE TABLE IF NOT EXISTS Schedule (
    schedule_Id INT PRIMARY KEY,
    course_Id INT,
    classroom_Id INT,
    schedule_Date DATE,
    start_Time TIME,
    end_Time TIME,
    FOREIGN KEY (course_Id) REFERENCES Course(course_Id),
    FOREIGN KEY (classroom_Id) REFERENCES Classroom(classroom_Id)
);

-- Creating Grade table
CREATE TABLE IF NOT EXISTS Grade (
    grade_Id INT PRIMARY KEY,
    student_Id INT,
    course_Id INT,
    grade_Value DECIMAL(5, 2),
    term VARCHAR(50),
    comments TEXT,
    FOREIGN KEY (student_Id) REFERENCES Student(student_Id),
    FOREIGN KEY (course_Id) REFERENCES Course(course_Id)
);

-- Creating Assignments table
CREATE TABLE IF NOT EXISTS Assignments (
    assignment_Id INT PRIMARY KEY,
    course_Id INT,
    title VARCHAR(100),
    description TEXT,
    due_Date DATE,
    status ENUM('Assigned', 'Completed', 'Graded'),
    teacher_Id INT,
    FOREIGN KEY (course_Id) REFERENCES Course(course_Id),
    FOREIGN KEY (teacher_Id) REFERENCES Teacher(teacher_Id)
);

-- Creating Enrollments table
CREATE TABLE IF NOT EXISTS Enrollments (
    enrollment_Id INT PRIMARY KEY,
    student_Id INT,
    course_Id INT,
    enrollment_Date DATE,
    FOREIGN KEY (student_Id) REFERENCES Student(student_Id),
    FOREIGN KEY (course_Id) REFERENCES Course(course_Id)
);

-- Creating Attendance table
CREATE TABLE IF NOT EXISTS Attendance (
    attendance_Id INT PRIMARY KEY,
    student_Id INT,
    date DATE,
    status ENUM('Present', 'Absent', 'Tardy'),
    remarks TEXT,
    recorded_by INT,
    FOREIGN KEY (student_Id) REFERENCES Student(student_Id)
);

-- Creating Announcements table
CREATE TABLE IF NOT EXISTS Announcements (
    announcement_Id INT PRIMARY KEY,
    title VARCHAR(100),
    content TEXT,
    audience ENUM('School_Wide', 'Grade_Specific', 'Role_Specific') NOT NULL,
    target_grade INT NULL,
    target_role INT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (target_role) REFERENCES Roles_Permission(role_Id),
    FOREIGN KEY (target_grade) REFERENCES Grade(grade_Id)
);

-- Creating Library table
CREATE TABLE IF NOT EXISTS Library (
    book_Id INT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    publication_Year INT,
    genre VARCHAR(50),
    availability ENUM('Available', 'Checked Out')
);

-- Creating Library_Transactions table
CREATE TABLE IF NOT EXISTS Library_Transactions (
    transaction_Id INT PRIMARY KEY,
    book_Id INT,
    student_Id INT,
    checkout_Date DATE,
    return_Date DATE,
    FOREIGN KEY (book_Id) REFERENCES Library(book_Id),
    FOREIGN KEY (student_Id) REFERENCES Student(student_Id)
);

-- Creating Medical_Record table
CREATE TABLE IF NOT EXISTS Medical_Record (
    record_Id INT PRIMARY KEY,
    student_Id INT,
    allergies TEXT,
    medical_Conditions TEXT,
    emergency_Contact_name VARCHAR(100),
    FOREIGN KEY (student_Id) REFERENCES Student(student_Id)
);

-- Creating Schools table
CREATE TABLE IF NOT EXISTS Schools (
    school_Id INT PRIMARY KEY,
    school_name VARCHAR(50),
    school_address VARCHAR(100),
    school_phone_number VARCHAR(15)
);

-- Creating Clubs table
CREATE TABLE IF NOT EXISTS Clubs (
    club_Id INT PRIMARY KEY,
    club_name VARCHAR(50),
    club_director INT,
    FOREIGN KEY (club_director) REFERENCES Teacher(teacher_Id)
);

-- Creating Awards table
CREATE TABLE IF NOT EXISTS Awards (
    award_Id INT PRIMARY KEY,
    award_name VARCHAR(50),
    award_winner INT,
    FOREIGN KEY (award_winner) REFERENCES Student(student_Id)
);
