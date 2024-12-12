import axios from 'axios';
import { useState } from 'react';

export default function Home() {
    const [file, setFile] = useState(null);
    const [quizzes, setQuizzes] = useState([]);

    const uploadFile = async () => {
        const formData = new FormData();
        formData.append('file', file);

        const response = await axios.post('http://localhost:8080/api/quiz/upload', formData, {
            headers: { 'Content-Type': 'multipart/form-data' },
        });
        setQuizzes(response.data);
    };

    const fetchQuizzes = async () => {
        const response = await axios.get('http://localhost:8080/api/quiz');
        setQuizzes(response.data);
    };

    return (
        <div>
            <h1>Quiz App</h1>
            <input type="file" onChange={(e) => setFile(e.target.files[0])} />
            <button onClick={uploadFile}>Upload Quiz</button>
            <button onClick={fetchQuizzes}>View Quizzes</button>

            <ul>
                {quizzes.map((quiz, index) => (
                    <li key={index}>
                        <strong>Question:</strong> {quiz.question} <br />
                        A: {quiz.optionA}, B: {quiz.optionB}, C: {quiz.optionC}, D: {quiz.optionD} <br />
                        <strong>Answer:</strong> {quiz.correctAnswer}
                    </li>
                ))}
            </ul>
        </div>
    );
}
