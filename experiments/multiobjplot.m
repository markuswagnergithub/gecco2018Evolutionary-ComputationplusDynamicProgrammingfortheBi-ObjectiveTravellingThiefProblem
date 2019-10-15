
inputfolder = 'eil76';
outputfolder = 'test';

files = dir(strcat(inputfolder,'/eil76_*.txt'));

x= [];
g={};

fid=fopen('eil76_output.txt','w');


for idx = 1: numel(files)

    name = files(idx).name;
    out = transpose(bestreward(inputfolder, name));
    fprintf(fid, '%s\n', name);
    fprintf(fid, '%f\n', out);
    
    out = padarray(out, 200-length(out), NaN, 'post');
    
    g{end+1}=name;
    x=[x out];

end

fclose(fid);

boxplot(x, 'labels',g, 'LabelOrientation','inline');
% xlabel('25 Buoys')
% title('50 Frequencies')
ylabel('Reward');

