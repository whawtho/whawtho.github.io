// Mod5Encryption.cpp : This file contains the 'main' function. Program execution begins and ends there. 
// Zeb Hawthorne | CS-405 artifact | CS-499 Milstone Four

#include <cassert>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <ctime>
#include <string>

// Original XOR encryption function (unchanged)

std::string encrypt_decrypt(const std::string& source, const std::string& key)
{
    // get lengths now instead of calling the function every time.
    const auto key_length = key.length();
    const auto source_length = source.length();

    // assert that input data is good
    assert(key_length > 0);
    assert(source_length > 0);

    std::string output = source;

    // loop through the source string char by char
    for (size_t i = 0; i < source_length; ++i)
    { // TODO: student need to change the next line from output[i] = source[i]
      // transform each character based on an xor of the key modded constrained to key length using a mod
        output[i] = source[i] ^ key[i % key_length];
    }

    // output length must equal our source length
    assert(output.length() == source_length);

    // return the transformed string
    return output;
}

// Original file helpers (unchanged)

std::string read_file(const std::string& filename)
{
    std::ifstream file(filename);
    std::ostringstream ss;

    if (!file.is_open()) {
        std::cerr << "Error: Could not open file " << filename << std::endl;

        return "";
    }

    ss << file.rdbuf(); // Read entire file into stream
    return ss.str();    // Return file content as string
}

std::string get_student_name(const std::string& string_data)
{
    std::string student_name;

    // find the first newline
    size_t pos = string_data.find('\n');
    // find a newline
    if (pos != std::string::npos)
    { // copy that substring as the student name
        student_name = string_data.substr(0, pos);
    }

    return student_name;
}

// Original save function kept for contrast with database approach.
// (The calls in main will be commented out as part of the enhancement)
void save_data_file(const std::string& filename, const std::string& student_name, const std::string& key, const std::string& data)
{
    //  file format
    //  Line 1: student name
    //  Line 2: timestamp (yyyy-mm-dd)
    //  Line 3: key used
    //  Line 4+: data

    std::ofstream file(filename);
    if (file.is_open())
    {
        // Line 1: student name
        file << student_name << std::endl;

        // Line 2: timestamp (yyyy-mm-dd)
        time_t now = time(nullptr);
        tm local_time;
        localtime_s(&local_time, &now);
        file << std::put_time(&local_time, "%Y-%m-%d") << std::endl;

        // Line 3: key used
        file << key << std::endl;

        // Line 4+: encrypted/decrypted data
        file << data << std::endl;

        file.close();
    }
    else
    {
        std::cerr << "Error: Could not open " << filename << " for writing." << std::endl;
    }

}

// DATABASE ENHANCEMENT: flat-file "database" for encrypted records

// helper to get current date as yyyy-mm-dd
std::string current_date_string()
{
    time_t now = time(nullptr);
    tm local_time;
    localtime_s(&local_time, &now);
    std::ostringstream os;
    os << std::put_time(&local_time, "%Y-%m-%d");
    return os.str();
}

// append one record to a flat-file database
// record format (single line, pipe-delimited):
//   studentName | date | key | dataSize | preview
void append_record_to_database(const std::string& dbFilename,
    const std::string& studentName,
    const std::string& key,
    const std::string& encryptedData)
{
    std::ofstream db(dbFilename, std::ios::app);
    if (!db.is_open())
    {
        std::cerr << "Error: Could not open database file " << dbFilename << " for writing." << std::endl;
        return;
    }

    const std::string date = current_date_string();
    const std::size_t dataSize = encryptedData.size();
    const std::size_t maxPreview = 40;
    const std::string preview = encryptedData.substr(0, maxPreview);

    db << studentName << " | "
        << date << " | "
        << key << " | "
        << dataSize << " | "
        << preview << std::endl;
}

// read back the database file and print all stored records
void print_database_records(const std::string& dbFilename)
{
    std::ifstream db(dbFilename);
    if (!db.is_open())
    {
        std::cerr << "No database file found yet (" << dbFilename << ")." << std::endl;
        return;
    }

    std::cout << "\n--- Encryption Record Database ---" << std::endl;

    std::string line;
    while (std::getline(db, line))
    {
        if (!line.empty())
        {
            std::cout << line << std::endl;
        }
    }

    std::cout << "----------------------------------" << std::endl;
}

// main (modified to use the database instead of output files)

int main()
{
    std::cout << "Encyption Decryption Test!" << std::endl;

    // input file format
    // Line 1: <students name>
    // Line 2: <Lorem Ipsum Generator website used> https://pirateipsum.me/ (could be https://www.lipsum.com/ or one of https://www.shopify.com/partners/blog/79940998-15-funny-lorem-ipsum-generators-to-shake-up-your-design-mockups)
    // Lines 3+: <lorem ipsum generated with 3 paragraphs> 
    //  Fire in the hole bowsprit Jack Tar gally holystone sloop grog heave to grapple Sea Legs. Gally hearties case shot crimp spirits pillage galleon chase guns skysail yo-ho-ho. Jury mast coxswain measured fer yer chains man-of-war Privateer yardarm aft handsomely Jolly Roger mutiny.
    //  Hulk coffer doubloon Shiver me timbers long clothes skysail Nelsons folly reef sails Jack Tar Davy Jones' Locker. Splice the main brace ye fathom me bilge water walk the plank bowsprit gun Blimey wench. Parrel Gold Road clap of thunder Shiver me timbers hempen halter yardarm grapple wench bilged on her anchor American Main.
    //  Brigantine coxswain interloper jolly boat heave down cutlass crow's nest wherry dance the hempen jig spirits. Interloper Sea Legs plunder shrouds knave sloop run a shot across the bow Jack Ketch mutiny barkadeer. Heave to gun matey Arr draft jolly boat marooned Cat o'nine tails topsail Blimey.

    const std::string file_name = "inputdatafile.txt";
    const std::string encrypted_file_name = "encrypteddatafile.txt";
    const std::string decrypted_file_name = "decrytpteddatafile.txt";
    const std::string database_file_name = "encryption_database.txt";
    const std::string source_string = read_file(file_name);
    const std::string key = "password";

    // get the student name from the data file
    const std::string student_name = get_student_name(source_string);

    // encrypt sourceString with key
    const std::string encrypted_string = encrypt_decrypt(source_string, key);

    // decrypt encryptedString with key
    const std::string decrypted_string = encrypt_decrypt(encrypted_string, key);

    // LEGACY OUTPUT (left in place but commented out for contrast)

    // save encrypted_string to file (original Module 5 behavior)
    // save_data_file(encrypted_file_name, student_name, key, encrypted_string);

    // save decrypted_string to file (original Module 5 behavior)
    // save_data_file(decrypted_file_name, student_name, key, decrypted_string);

    // DATABASE ENHANCEMENT
    // Instead of only writing plain output files, store a compact record
    // in a flat-file "database" that can be read and reviewed later.

    append_record_to_database(database_file_name, student_name, key, encrypted_string);

    std::cout << "Read File:  " << file_name << std::endl;
    std::cout << "Database:   " << database_file_name << " (encrypted record appended)" << std::endl;

    // Show all stored records so far
    print_database_records(database_file_name);

    // students submit input file, encrypted file, decrypted file, source code file, and key used
    // (encrypted/decrypted text files are no longer required for the enhancement,
    //  but the save_data_file function remains for comparison.)

    return 0;
}
