import { Alert, Snackbar } from "@mui/material";
import axios from "axios";
import { ChangeEvent, useState } from "react";
import styles from "./FileInput.module.scss";

type Props = {
    onPost: Function
}

const FileInput = ({ onPost }: Props) => {
    const [file, setFile] = useState<File>();
    const [open, setOpen] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

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
        .catch((e) => {
            setErrorMessage(e.response.data);
            setOpen(true);
        });
    }

    const handleClose = () => {
        setOpen(false);
    }

    return (
        <div className={styles.container}>
            <Snackbar open={open} autoHideDuration={3000} onClose={handleClose} anchorOrigin={{ vertical: "top", horizontal: "center" }}>
                <Alert onClose={handleClose} severity="error" sx={{ width: '100%' }}>
                    {errorMessage}
                </Alert>
            </Snackbar>
            <label>
                {(file && ((file.name.length > 22 && (file.name.substring(0, 22) + "...")) || file.name)) || "Select file..."}
                <input type="file" onChange={handleFileChange} />
            </label>
            <button className={styles.upload_button} onClick={handleUploadClick}>Upload</button>
        </div>
    );
}
export default FileInput;