-- Q1 manage user accounts
INSERT INTO User (user_Id, role_Id, status)
VALUES (?, ?, 'active')
ON DUPLICATE KEY UPDATE status = 'deactivated';

-- Q2 and Q3 creating students and teachers
INSERT INTO Student (
    student_Id, 
    first_Name, 
    middle_Name, 
    last_Name, 
    date_Of_Birth, 
    gender, 
    address, 
    phone_Number, 
    email, 
    enrollment_Date, 
    grade_Level, 
    guardian_First_Name, 
    guardian_Last_Name, 
    guardian_Id, 
    homeroom_Teacher_Id, 
    role_Id
) 
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

INSERT INTO Guardian (
    guardian_Id, 
    first_Name, 
    last_Name, 
    relationship_To_Student, 
    phone_Number, 
    email, 
    address, 
    role_Id
) 
VALUES (?, ?, ?, ?, ?, ?, ?, ?);



-- Q4 create and assign courses
INSERT INTO Course (course_Name, description, grade_Level, subject_Id)
VALUES (?, ?, ?, ?);

INSERT INTO Teaches (teacher_Id, course_Id)
VALUES (?, ?);

-- Q5 manage attendance
INSERT INTO Attendance (student_Id, date, status)
VALUES (?, ?, ?);


-- Q6 generate attendance report by grade level
SELECT 
    S.grade_Level, 
    COUNT(A.status) AS total_Absences
FROM 
    Attendance A
JOIN 
    Student S ON S.student_Id = A.student_Id
WHERE 
    A.status = 'Absent'
GROUP BY 
    S.grade_Level;
    
    
-- Q7 
INSERT INTO Schedule (course_Id, classroom_Id, schedule_Date, start_Time, end_Time)
VALUES (?, ?, ?, ?, ?);


