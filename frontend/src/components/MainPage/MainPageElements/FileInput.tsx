import axios from "axios";
import { ChangeEvent, useState } from "react";

type Props = {
    onPost: Function
}

const FileInput = ({ onPost }: Props) => {
    const [file, setFile] = useState<File>();

    const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setFile(e.target.files[0]);
        }
    }

    const handleUploadClick = () => {
        if (!file) {
            return;
        }

        axios.post('/api/employee/upload', {
            file: file
        },
        {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        })
        .then(() => {
            onPost();
        })
        .catch(console.log);
    }

    return (
        <div>
            <input type="file" onChange={handleFileChange} />
            <button onClick={handleUploadClick}>Upload</button>
        </div>
    );
}
export default FileInput;