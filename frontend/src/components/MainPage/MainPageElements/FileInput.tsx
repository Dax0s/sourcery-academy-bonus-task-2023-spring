import { Alert, Snackbar } from "@mui/material";
import axios from "axios";
import { ChangeEvent, useState } from "react";
import styles from "./FileInput.module.scss";

type Props = {
    onPost: Function
}

const FileInput = ({ onPost }: Props) => {
    const [file, setFile] = useState<File>();
    const [errorOpen, setErrorOpen] = useState(false);
    const [successOpen, setSuccessOpen] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");

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
        .then((res) => {
            onPost();

            console.log(res);
            setSuccessMessage(res.data);
            setErrorOpen(false);
            setSuccessOpen(true);
        })
        .catch((res) => {
            setErrorMessage(res.response.data);

            setSuccessOpen(false);
            setErrorOpen(true);
        });
    }

    const handleClose = () => {
        setErrorOpen(false);
        setSuccessOpen(false);
    }

    return (
        <div className={styles.container}>
            <Snackbar open={errorOpen} autoHideDuration={3000} onClose={handleClose} anchorOrigin={{ vertical: "top", horizontal: "center" }}>
                <Alert onClose={handleClose} severity="error" sx={{ width: '100%' }}>
                    {errorMessage}
                </Alert>
            </Snackbar>

            <Snackbar open={successOpen} autoHideDuration={3000} onClose={handleClose} anchorOrigin={{ vertical: "top", horizontal: "center" }}>
                <Alert onClose={handleClose} severity="success" sx={{ width: '100%' }}>
                    {successMessage}
                </Alert>
            </Snackbar>
            <label>
                {(file && ((file.name.length > 20 && (file.name.substring(0, 20) + "...")) || file.name)) || "Select file..."}
                <input type="file" onChange={handleFileChange} />
            </label>
            <button className={styles.upload_button} onClick={handleUploadClick}>Upload</button>
        </div>
    );
}
export default FileInput;