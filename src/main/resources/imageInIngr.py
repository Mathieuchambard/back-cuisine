import os
from PIL import Image
import base64
import json
import io

def resize_and_encode_image(image_path, target_width, target_height):
    # Ouvrir l'image
    with Image.open(image_path) as img:
        # Convertir l'image en mode RVB
        img = img.convert('RGB')
        # Redimensionner l'image
        img_resized = img.resize((target_width, target_height), Image.ANTIALIAS)
        # Encoder l'image en base64
        buffered = io.BytesIO()
        img_resized.save(buffered, format="PNG")  # Vous pouvez spécifier le format souhaité ici
        encoded_image = base64.b64encode(buffered.getvalue()).decode('utf-8')
        return encoded_image


def process_json_files(folder_path, target_width, target_height):
    # Parcourir tous les fichiers dans le dossier
    for filename in os.listdir(folder_path):
        if filename.endswith(".json"):  # Vérifier si c'est un fichier JSON
            json_file_path = os.path.join(folder_path, filename)
            with open(json_file_path, "r") as json_file:
                # Charger les données JSON
                data = json.load(json_file)

            name = os.path.splitext(os.path.basename(json_file_path))[0]

            image_path = os.path.join(folder_path, "images/", name+".png")

            if os.path.exists(image_path):  # Vérifier si le fichier d'image existe
                # Réduire la résolution de l'image et l'encoder en base64
                encoded_image = resize_and_encode_image(image_path, target_width, target_height)
                # Ajouter l'image encodée aux données JSON
                data["image"] = "data:image/png;base64," + encoded_image
                # Écrire les données JSON mises à jour dans le fichier
                with open(json_file_path, "w") as json_file:
                    json.dump(data, json_file, indent=4)

if __name__ == "__main__":
    folder_path = "jsonIngredient"  # Chemin du dossier contenant les fichiers JSON et les images
    target_width = 300  # Largeur cible de l'image redimensionnée
    target_height = 300  # Hauteur cible de l'image redimensionnée
    process_json_files(folder_path, target_width, target_height)
